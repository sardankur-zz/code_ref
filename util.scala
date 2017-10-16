package util

import scala.io.Source;
import scala.collection.JavaConversions._

import ds._;

object graph_utilities {
    def readCostGraph(filename : String, bidirect: Boolean = false) : Graph[Int, Int, Int] = {
        val g: Graph[Int, Int, Int] = new Graph[Int, Int, Int](bidirect);
        for(line <- Source.fromFile(filename).getLines) {            
            val readline = line.split(" ")
            val _1_id = readline(0).toInt;
            for(i <- 1 until readline.length) {
                val id_and_cost = readline(i).split("=");                              
                g.addEdge(_1_id, id_and_cost(0).toInt, id_and_cost(1).toInt);
            }            
        }
        return g;
    }
}