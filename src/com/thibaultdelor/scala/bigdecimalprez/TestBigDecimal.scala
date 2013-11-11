package com.thibaultdelor.scala.bigdecimalprez

import scala.util.Random
import java.math.MathContext
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator

object TestBigDecimal {

  val ITER: Int = 1000000
  def main(args: Array[String]): Unit = {
    val myBds: Array[BigDecimal] =
      for (i <- Array.range(1, ITER))
        yield BigDecimal(Random.nextDouble, MathContext.DECIMAL32);
    val myDoubles = myBds.map(_.toDouble)

    val bdSize = ObjectSizeCalculator.getObjectSize(myBds)
    val doubleSize = ObjectSizeCalculator.getObjectSize(myDoubles)

    println("BigDecimal size : " + humanReadableByteCount(bdSize))
    println("Double size     : " + humanReadableByteCount(doubleSize))
    println("Ratios : " + bdSize.toDouble / doubleSize.toDouble)
  }

  def humanReadableByteCount(bytes: Long): String = {
    if (bytes < 1024) return bytes + " B";
    val exp = (Math.log(bytes) / Math.log(1024)).toInt

    "%.1f %sB".format(bytes / Math.pow(1024, exp), "KMGTPE".charAt(exp - 1))
  }

}