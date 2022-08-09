package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.CyclicPertGraphException;

import java.util.*;

public class PertGraph {
    private final Map<Integer, PertNode> nodes;
    private final Map<PertTask, PertEdge> edges;

    public PertGraph(){
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    public Map<Integer, PertNode> getNodes(){
        return new HashMap<>(nodes);
    }

    public void addNode(PertNode node) {
        nodes.put(node.getPosition(), node);
    }

    public void addEdge(int from, int to, PertTask task) throws CyclicPertGraphException {
        PertNode originNode = getOrCreateNode(from);
        PertNode targetNode = getOrCreateNode(to);
        PertEdge edge = new PertEdge(from, to, task);
        edges.put(task, edge);
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
        insertEdge(originNode, targetNode, edge);
    }

    public PertNode addNode (int pos, PertTask prior, PertTask task) throws CyclicPertGraphException {
        PertEdge priorEdge = getExistingEdge(prior);
        PertNode originNode = getOrCreateNode(priorEdge.getTarget());
        PertNode targetNode = getOrCreateNode(pos);
        PertEdge newEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), task);
        edges.put(task, newEdge);
        insertEdge(originNode, targetNode, newEdge);
        return targetNode;
    }

    public PertTask addFake(PertNode targetNode, PertTask task) throws CyclicPertGraphException {
        PertTask fakeTask = new FakeTask(task);
        PertEdge originalEdge = edges.get(task);
        PertNode originNode = nodes.get(originalEdge.getTarget());
        PertEdge fakeEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), fakeTask);
        edges.put(fakeTask, fakeEdge);

        insertEdge(originNode, targetNode, fakeEdge);
        return fakeTask;
    }

    private void insertEdge(PertNode originNode, PertNode targetNode, PertEdge edge){
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
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
        PertEdge fakeEdge = edges.get(fakeTask);
        PertNode originNode = nodes.get(fakeEdge.getOrigin());
        PertNode targetNode = nodes.get(fakeEdge.getTarget());

        if(targetNode.hasOneOutgoingEdge()){
            PertNode newOrigin = findOriginToReattach(originNode, targetNode.getPosition());
            if(newOrigin != null){
                redirectEdge(originNode, targetNode, fakeEdge, newOrigin);
                edges.remove(fakeTask);
                if(originNode.isEmpty()) nodes.remove(originNode.getPosition());
            }
        }
    }

    private void redirectEdge(PertNode fakeOrigin, PertNode targetNode, PertEdge fakeEdge, PertNode newOrigin){
        PertEdge edgeToRedirect = newOrigin.getOutGoingEdgeWithTarget(fakeOrigin.getPosition());
        detachEdge(fakeOrigin, targetNode, fakeEdge);
        reattachEdge(edgeToRedirect, targetNode);
    }

    private void detachEdge(PertNode originNode, PertNode targetNode, PertEdge edge){
        originNode.removeOutgoingEdge(edge);
        targetNode.removeIncomingEdge(edge);
    }

    private void reattachEdge(PertEdge edge, PertNode targetNode){
        edge.setTarget(targetNode.getPosition());
        targetNode.addIncomingEdge(edge);
    }

    private PertNode findOriginToReattach(PertNode previousOrigin, int target){
        for(PertEdge incomingEdges : previousOrigin.getIncomingEdges()){
            PertNode originCandidate = nodes.get(incomingEdges.getOrigin());
            if(!originCandidate.hasOutgoinEdge(target)) return originCandidate;
        }
        return null;
    }
}
