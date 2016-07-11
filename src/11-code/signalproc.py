
# Note: all signals have a getValueAt(i) method.

class StepSignal(object):
   def __init__(self, xs, ys):
      """
      xs, ys are both iterables.
      """
      self._xs = list(xs)
      self._ys = list(ys)
      for i in xrange(1, len(self._xs)):
         if self._xs[i-1] >= self._xs[i]:
            raise Exception("X values must be unique and ascending")

   def firstMeasurementPoint(self):
      return self._xs[0]

   def lastMeasurementPoint(self):
      return self._xs[-1]

   def getValueAt(self, x):
      if (x < self._xs[0] or x > self._xs[-1]):
         return 0
      if x == self._xs[-1]:
         return self._ys[-1]
      answer = 0.0
      for i in xrange(1, len(self._xs)):
         x0 = self._xs[i-1]
         x1 = self._xs[i]
         if (x0 <= x < x1):
            answer = self._ys[i-1]
            break
      return answer

class LinearInterpolatedSignal(object):
   def __init__(self, xs, ys):
      """
      xs, ys are both iterables.
      """
      self._xs = list(xs)
      self._ys = list(ys)
      for i in xrange(1, len(self._xs)):
         if self._xs[i] <= self._xs[i-1]:
            raise Exception("X values must be unique and ascending")

   def firstMeasurementPoint(self):
      return self._xs[0]

   def lastMeasurementPoint(self):
      return self._xs[-1]

   def getValueAt(self, x):
      if (x < self._xs[0] or x > self._xs[-1]):
         return 0
      if x == self._xs[-1]:
         return self._ys[-1]
      answer = 0.0
      for i in xrange(1, len(self._xs)):
         x0 = self._xs[i-1]
         x1 = self._xs[i]
         y0 = self._ys[i-1]
         y1 = self._ys[i]
         if (x0 <= x < x1):
            answer = y0 + ( (y1 - y0) * ((x - x0) / float(x1 - x0)))
            break
      return answer

class SignalProcessor(object):
   def averageValue(self, signal, frm, to, numSamples):
      """computes the average value of a signal in the range (frm, to),
      using numSamples sample points.
      frm, to, numSamples: numbers
      signal: must have a getValueAt(n) method.
      """

      step = float(to - frm) / numSamples
      s = 0
      for i in xrange(0, numSamples):
         x = frm + (step * i)
         s += signal.getValueAt(x)
      return s / numSamples

def main():
   print not False
   xs = [0, 0.6, 1.0, 1.5, 1.7, 2.1, 2.6, 3.0, 3.4, 4.1]
   ys = [1, 2.1, 2.3, 2.1, 2.3, 2.9, 2.6, 4.0, 4.4, 3.9]

   steps = StepSignal(xs, ys)
   linear = LinearInterpolatedSignal(xs, ys)

   proc = SignalProcessor()
   print "Average of steps:", proc.averageValue(steps, 0, 4, 100)
   print "Average of linear:", proc.averageValue(linear, 0, 4, 100)


main()
