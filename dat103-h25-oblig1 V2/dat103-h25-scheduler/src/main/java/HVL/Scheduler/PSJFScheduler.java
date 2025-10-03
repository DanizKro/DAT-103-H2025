package HVL.Scheduler;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;


public class PSJFScheduler implements Scheduler {

    private final Comparator<Task> byRemaningThenId =
    		Comparator.comparingInt(Task::getRemaining)
    		.thenComparingInt(Task::getId);
    private final Queue<Task> ready = new PriorityQueue<>(byRemaningThenId);
    private Task selected;
    

    PSJFScheduler() {    }

    @Override
    public Optional<Integer> scheduled() {
        if(selected == null) return Optional.empty();
        return Optional.of(selected.getId());
    }

    @Override
    public List<Integer> ready() {
        return ready.stream()
        		.sorted(byRemaningThenId)
        		.map(Task::getId)
        		.toList();
    }

    // Subtask 2(a): Complete the implementation of Preemptive Shortest Job First    
    @Override
    public void addTask(Task task) {	
    	ready.add(task);
    	if(selected != null && !selected.isDone() && task.getRemaining() < selected.getRemaining()){
    		selected.stop();
    		ready.add(selected);
    		selected = ready.poll();
    		if(selected != null) selected.start();
    	}
    }

    @Override
    public void schedule() {
    	
    	if(selected != null && selected.isDone()) {
    		selected = null;
    	}
        if(selected == null) {                
            selected = ready.poll();          
            if(selected == null) {            
                return;                       
            }                                 
            selected.start();                 
        } else {                              
	    // Subtask 2(a): Complete the implementation of Preemptive Shortest Job First    
        	Task shortest = ready.peek();
        	if(shortest != null && shortest.getRemaining() < selected.getRemaining()) {
        		selected.stop();
        		ready.add(selected);
        		selected = ready.poll();
        		if(selected != null) selected.start();
        	}
        }                                    
    }

}