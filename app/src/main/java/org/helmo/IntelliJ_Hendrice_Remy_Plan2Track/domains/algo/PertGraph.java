package org.helmo.IntelliJ_Hendrice_Remy_Plan2Track.domains.algo;

import java.util.*;

public class PertGraph {
    private final Map<Integer, PertNode> nodes;
    private final Map<PertTask, PertEdge> edges;

    public PertGraph(){
        nodes = new HashMap<>();
        addNode(new PertNode(1));
        edges = new HashMap<>();
    }

    public Map<Integer, PertNode> getNodes(){
        return new HashMap<>(nodes);
    }

    public Set<PertEdge> getEdges(){return new HashSet<>(edges.values());}

    public PertNode getNode(int position){return nodes.get(position);}

    public void addNode(PertNode node) {
        nodes.put(node.getPosition(), node);
    }

    public void addEdge(int from, int to, PertTask task) throws CyclicGraphException {
        PertNode originNode = getOrCreateNode(from);
        PertNode targetNode = getOrCreateNode(to);
        PertEdge edge = new PertEdge(from, to, task);
        edges.put(task, edge);
        originNode.addOutGoingEdge(edge);
        targetNode.addIncomingEdge(edge);
        insertEdge(originNode, targetNode, edge);
    }

    public PertNode addNode (int pos, PertTask prior, PertTask task) throws CyclicGraphException {
        PertEdge priorEdge = getExistingEdge(prior);
        PertNode originNode = getOrCreateNode(priorEdge.getTarget());
        PertNode targetNode = getOrCreateNode(pos);
        PertEdge newEdge = new PertEdge(originNode.getPosition(), targetNode.getPosition(), task);
        edges.put(task, newEdge);
        insertEdge(originNode, targetNode, newEdge);
        return targetNode;
    }

    public PertTask addFake(PertNode targetNode, PertTask task) throws CyclicGraphException {
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
            if(!originCandidate.hasOutgoingEdge(target)) return originCandidate;
        }
        return null;
    }
}
