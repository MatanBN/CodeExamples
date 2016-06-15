package game;

import animations.AnimationRunner;
import animations.MenuAnimation;
import animations.HighScoresAnimation;
import animations.GameLevel;
import animations.StopScreenDecorator;
import animations.EndScreen;


import animations.Task;

import animations.Menu;
import biuoop.DialogManager;

import biuoop.KeyboardSensor;
import gamelevels.LevelInformation;
import geometry.Rectangle;
import leveldevelopment.LevelSpecificationReader;
import sprites.LiveIndicator;
import sprites.ScoreIndicator;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The GameFlow class controls the different levels of the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private int lives;
    private ScoreIndicator score;
    private HighScoresTable scoresTable;
    private List<LevelInformation> levels;


    /**
     * Constructor for the GameFlow class.
     *
     * @param ar          The Animation Runner of the game.
     * @param ks          The KeyboardSensor of the game.
     * @param lives       is the number of live.
     * @param scoresTable is the HighScores table of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable scoresTable) {
        this.ar = ar;
        this.ks = ks;
        this.lives = lives;
        this.score = new ScoreIndicator();
        this.scoresTable = scoresTable;
    }

    /**
     * chooseTask creates the tasks of the game, calls the menu to display them,
     * and runs the task that the user chooses.
     */
    public void chooseTask() {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(ks, ar, "please choose:");

        //Anonymous classes that defines each task's run method.
        Task<Void> playGame = new Task<Void>() {
            @Override
            public Void run() {
                runLevels(levels);
                return null;
            }
        };

        Task<Void> hiScores = new Task<Void>() {
            @Override
            public Void run() {
                ar.run(new HighScoresAnimation(scoresTable, "i", ks));
                if (ks.isPressed(KeyboardSensor.SPACE_KEY)) {
                    chooseTask();
                }
                return null;
            }
        };

        Task<Void> quitGame = new Task<Void>() {
            @Override
            public Void run() {
                Runtime.getRuntime().exit(1);
                return null;
            }
        };

        // Adding the tasks to the tasks list.
        menu.addSelection("h", "High scores", hiScores);
        menu.addSubMenu("s", "Play", playGame);
        menu.addSelection("q", "Quit", quitGame);

        while (true) {
            ar.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * LevelSet class is a private class for a level set.
     */
    private class LevelSet {
        private String key; // The key to press to choose the level set.
        private String name; // The name of the level set.
        private String levelPath; // The file of the levels for the level set.
        private Task<Void> setTask; // The task of the levelSet.

        /**
         * Constructor for the level set.
         *
         * @param key  the key to press to choose the level set.
         * @param name The name of the level set.
         */
        public LevelSet(String key, String name) {
            this.key = key;
            this.name = name;
        }

        /**
         * getKey returns the key to choose the level set.
         *
         * @return the key to choose the level set.
         */
        public String getKey() {
            return key;
        }

        /**
         * getName returns the name of the level set.
         *
         * @return the name of the level set.
         */
        public String getName() {
            return name;
        }

        /**
         * getLevelPath returns the file of the levels for the level set.
         *
         * @return The file of the levels for the level set.
         */
        public String getLevelPath() {
            return levelPath;
        }

        /**
         * setLevelPath sets the path for the file of the levels for the level set.
         *
         * @param theLevelPath the file of the levels for the level set.
         */
        public void setLevelPath(String theLevelPath) {
            this.levelPath = theLevelPath;
        }

        /**
         * getLevelTask gets the set of levels of the level set.
         *
         * @return the set of levels of the level set.
         */
        public Task<Void> getSetTask() {
            return setTask;
        }

        /**
         * setSetTask sets a list of level for the level set.
         */
        public void setSetTask() {
            setTask = new Task<Void>() {
                @Override
                public Void run() {
                    levels = getListOfLevels(getLevelPath());
                    runLevels(levels);
                    return null;
                }
            };
        }
    }

    /**
     * runLevels method runs a given list of levels.
     *
     * @param theLevels a list of levels.
     */
    public void runLevels(List<LevelInformation> theLevels) {
        LiveIndicator liveIndicator = new LiveIndicator(lives);
        score = new ScoreIndicator();
        for (LevelInformation levelInfo : theLevels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar);
            level.initialize(liveIndicator, score);
            while (level.getBlockCounter().getValue() != 0 && liveIndicator.getValue() != 0) {
                level.playOneTurn();
            }

            if (level.getBlockCounter().getValue() == 0) {
                score.increase(100);
            }

            if (liveIndicator.getValue() == 0) {
                break;
            }
        }
        ar.run(new StopScreenDecorator(ks, "o", new EndScreen(ks, liveIndicator, score)));
        DialogManager dialog = ar.getGui().getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        scoresTable.add(new ScoreInfo(name, score.getScore().getValue()));
        ar.run(new StopScreenDecorator(ks, "t", new HighScoresAnimation(scoresTable, "t", ks)));
        try {
            scoresTable.save(new File("highscores"));
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
        this.chooseTask();
    }



}
