package game;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * HighScoresTable is in charge of creating the high-scores table, saving and loading it from a file.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class HighScoresTable {
    private List<ScoreInfo> table;
    private int tableCapacity;


    /**
     * Constructor create an empty high-scores table with the specified size.
     *
     * @param size means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        table = new ArrayList<ScoreInfo>();
        tableCapacity = size;
    }

    /**
     * add is in charge of adding a new high-score to the table.
     *
     * @param score is the ScoreInfo to be added to the table.
     */
    public void add(ScoreInfo score) {
        if (table.size() == 0) {
            table.add(score);
        } else {
            List<ScoreInfo> newScoreInfo = new ArrayList<ScoreInfo>();
            int i = getRank(score.getScore());
            if (i < size()) {
                for (int j = 0; j < i; ++j) {
                    newScoreInfo.add(table.get(j));
                }
                newScoreInfo.add(score);
                for (int j = i; j < table.size(); ++j) {
                    newScoreInfo.add(table.get(j));
                }
                table = newScoreInfo;
            }
        }
    }

    /**
     * add is in charge of adding a high-score list.
     *
     * @param scores is a list of high-scores.
     */
    public void add(List<ScoreInfo> scores) {
        for (ScoreInfo score : scores) {
            this.add(score);
        }
    }

    /**
     * currentSize returns the size of the table.
     *
     * @return the size of the table.
     */
    public int currentSize() {
        return table.size();
    }

    /**
     * size return the table capacity.
     *
     * @return the table capacity.
     */
    public int size() {
        return this.tableCapacity;
    }

    /**
     * getHighScores returns the high-scores list.
     *
     * @return the high-scores list.
     */
    public List<ScoreInfo> getHighScores() {
        return table;
    }

    /**
     * getRank returns the rank of the current score.
     *
     * @param score is the current score.
     * @return the rank of the current score.
     */
    public int getRank(int score) {
        int i = 0;
        while (i < this.size() && i < table.size() && table.get(i).getScore() > score) {
            i++;
        }
        return i;

    }

    /**
     * clear is in charge of clearing the table.
     */
    public void clear() {
        table.clear();
    }

    /**
     * load is in charge of loading the table data from file.
     *
     * @param filename is the file that holds the table's data.
     * @throws IOException when the file can't get closed.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            int tableSize = ois.readInt();
            for (int i = 0; i < tableSize; ++i) {
                add((ScoreInfo) ois.readObject());
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            System.out.println("Error loading file");
        } catch (ClassNotFoundException e) {
            System.out.println("Class from file does not exist");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Error closing file");
                }
            }
        }

    }

    /**
     * save is in charge of saving the table data to the specified file.
     *
     * @param filename is the file that holds the table data.
     * @throws IOException when the file can't get closed.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeInt(table.size());
            for (ScoreInfo score : table) {
                oos.writeObject(score);
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Error closing file");
                }
            }
        }
    }

    /**
     * loadFromFile Read a table from file and return it.
     * If the file does not exist, or there is a problem with reading it,
     * an empty table is returned.
     *
     * @param filename is the file to read the table from.
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        List<ScoreInfo> ds = new ArrayList<ScoreInfo>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            int tableSize = ois.readInt();
            for (int i = 0; i < tableSize; ++i) {
                ds.add((ScoreInfo) ois.readObject());
            }
        } catch (IOException e) {
            System.out.println("Error loading from file");
        } catch (ClassNotFoundException e) {
            System.out.println("Class from file does not exist");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Error closing file");
                }
            }
        }
        HighScoresTable newScores = new HighScoresTable(ds.size());
        newScores.add(ds);
        return newScores;
    }

    /**
     * getScore returns a specific score from the table.
     *
     * @param index is the index of the score.
     * @return the score from the table.
     */
    public ScoreInfo getScore(int index) {
        return this.table.get(index);
    }
}
