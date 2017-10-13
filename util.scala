import scala.io.Source;
import scala.collection.mutable.Set;
import scala.collection.mutable.Map;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.HashSet;
import scala.collection.JavaConversions._

class Graph[ID, ND, ED] (bidirect : Boolean = false) {    

    class Node {    
        var id: ID = _;
        var data: ND = _;
        var prop: Map[String, scala.Any] = Map()

        override def equals (that : Any) : Boolean = 
        that match {
            case that: Node => that.id == this.id;
            case _ => false;
        }

        override def hashCode = id.hashCode

        override def toString: String = id.toString
    }

    class Edge {
        var id: ID = _;
        var data: ED = _;
        var _1: Node = _;
        var _2: Node = _;
        var prop: Map[String, scala.Any] = Map()

        override def equals (that : Any) : Boolean = 
        that match {
            case that: Edge => that._1 == this._1 && that._2 == this._2;
            case _ => false;
        }

        override def toString: String = bidirect match {
            case true => s"${_1.toString} <-> ${_2.toString}"
            case false => s"${_1.toString} -> ${_2.toString}"
        }
    }

    val nodeSet: Set[Node] = new scala.collection.mutable.HashSet;
    val edgeSet: Set[Edge] = new scala.collection.mutable.HashSet;

    private def addNode(id: ID) : Node = {
        val node: Node = new Node;    
        node.id = id;               
        nodeSet.add(node)
        node
    }

    def getNode(id: ID) : Option[Node] = {        
        nodeSet.forEach(node => 
            if(id == node.id) return Some(node)            
        )
        return None;
    }

    def addEdge(_1: ID, _2: ID, data: ED) : Edge = {    
        var node_1 : Option[Node] =  getNode(_1);
        var node_2 : Option[Node] =  getNode(_2);
        if (node_1 == None) node_1 = Some(addNode(_1));
        if (node_2 == None) node_2 = Some(addNode(_2));        
        val edge: Edge = new Edge
        edge._1 = node_1.get;
        edge._2 = node_2.get;
        edge.data = data
        edgeSet.add(edge);
        edge
    }    

    def getOuterEdges(node: Node) : List[Edge] = {
        var edges: ListBuffer[Edge] = ListBuffer();
        edgeSet.forEach(edge => if(edge._1 == node) edges += edge)
        if (bidirect) {            
            edgeSet.forEach(edge => if(edge._2 == node) edges += edge)
        }
        return edges.toList;
    }    

    def getNeighbors(node: Node) : List[Node] = {
        var nodes: ListBuffer[Node] = ListBuffer();
        edgeSet.forEach(edge => if(edge._1 == node) nodes += edge._2)
        if (bidirect) {            
            edgeSet.forEach(edge => if(edge._2 == node) nodes += edge._1)
        }
        return nodes.toList;
    }
}

object util {
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