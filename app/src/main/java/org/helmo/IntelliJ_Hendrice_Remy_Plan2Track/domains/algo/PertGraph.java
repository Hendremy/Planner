package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.CyclicPertGraphException;

import java.util.*;

public class PertGraph {
    private final Map<Integer, PertNode> nodes;
    private final Map<PertTask, PertEdge> edges;

    public PertGraph(){
        nodes = new HashMap<>();
        addNode(new PertNode(1));
        edges = new HashMap<>();
    }

    public void addNode(PertNode node) {
        nodes.put(node.getPosition(), node);
    }

    public void addEdge(int from, int to, PertTask task) throws CyclicPertGraphException {
        PertNode fromNode = getOrCreateNode(from);
        PertNode toNode = getOrCreateNode(to);
        PertEdge edge = new PertEdge(from, to, task);
        edges.put(task, edge);
        fromNode.addOutGoingEdge(edge);
        toNode.addIncomingEdge(edge);
    }

    public PertNode addNode (int pos, PertTask prior, PertTask task) throws CyclicPertGraphException {
        PertEdge priorEdge = getExistingEdge(prior);
        PertNode originNode = getOrCreateNode(priorEdge.getTarget());
        PertNode targetNode = getOrCreateNode(pos);
        PertEdge newEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), task);
        edges.put(task, newEdge);
        originNode.addOutGoingEdge(newEdge);
        targetNode.addIncomingEdge(newEdge);
        return targetNode;
    }

    public PertTask addFake(PertNode origin, PertTask task){
        PertTask fakeTask = new FakeTask(task);

        return fakeTask;
    }

    private PertNode getOrCreateNode(int position){
        PertNode node;
        if(nodes.containsKey(position)){
            node = nodes.get(position);
        }else{
            node = new PertNode(position);
            addNode(node);
        }
        return node;
    }

    private PertEdge getExistingEdge(PertTask task){
        return edges.get(task);
    }

    public void pruneFakeTask(PertTask fakeTask){
        PertEdge taskEdge = edges.get(fakeTask);
        PertNode originNode = nodes.get(taskEdge.getOrigin());
        PertNode targetNode = nodes.get(taskEdge.getTarget());

        if(targetNode.hasOneOutgoingEdge()){
            PertNode newOrigin = findOriginToReattach(originNode, targetNode.getPosition());
            if(newOrigin != null){
                //Supprimer la fake task & réattacher la newOrigin au targetNode
                originNode.removeOutgoingEdge(taskEdge);
                targetNode.removeIncomingEdge(taskEdge);
                edges.remove(fakeTask);

                PertEdge edgeToReattach = newOrigin.getOutGoingEdgeWithTarget(originNode.getPosition());
                edgeToReattach.setTarget(targetNode.getPosition());
                targetNode.addIncomingEdge(edgeToReattach);

                if(originNode.isEmpty()) nodes.remove(originNode.getPosition());
            }
        }
    }

    private PertNode findOriginToReattach(PertNode previousOrigin, int target){
        for(PertEdge incomingEdges : previousOrigin.getIncomingEdges()){
            PertNode originCandidate = nodes.get(incomingEdges.getOrigin());
            if(!originCandidate.hasOutgoinEdge(target)) return originCandidate;
        }
        return null;
    }
}
