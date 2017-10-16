import scala.collection.JavaConversions._

import util._;
import ds._;
import algorithm._;

object Test {

  def execute( function : => Unit ) : Unit = {
    println
    function
    println
  }
  
  def main(args: Array[String]): Unit = {      

    /*
    // DFS - uni directional    
    execute {      
      val g: Graph[Int, Int, Int] = graph_utilities.readCostGraph("data/dijkstra/input1.txt");        
      val source: g.Node = g.getNode(1).get    
      for(i <- dfs.execute_iter(g)(source)) {
        println(i.id)
      }      
    } 
           

    // DFS - bi directional
    execute {      
      val g: Graph[Int, Int, Int] = graph_utilities.readCostGraph("data/dijkstra/input1.txt", true);        
      val source: g.Node = g.getNode(1).get    
      for(i <- dfs.execute_iter(g)(source)) {
        println(i.id)
      }      
    }    
    */

    // BFS - uni directional
    execute {      
      val g: Graph[Int, Int, Int] = graph_utilities.readCostGraph("data/dijkstra/input1.txt");        
      val source: g.Node = g.getNode(1).get    
      for(i <- bfs.execute_iter(g)(source)) {
        println(i.id)
      }      
    }

    // BFS - bi directional
    execute {      
      val g: Graph[Int, Int, Int] = graph_utilities.readCostGraph("data/dijkstra/input1.txt", true);        
      val source: g.Node = g.getNode(1).get    
      for(i <- bfs.execute_iter(g)(source)) {
        println(i.id)
      }      
    }
  }
}