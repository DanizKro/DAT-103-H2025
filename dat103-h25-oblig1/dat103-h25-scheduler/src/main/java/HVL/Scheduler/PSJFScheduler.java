package HVL.Scheduler;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class PSJFScheduler implements Scheduler {

    private Queue<Task> ready;
    private Task selected;

    PSJFScheduler() {
        this.ready = new ArrayDeque<>();
	this.selected = null;
    }

    @Override
    public Optional<Integer> scheduled() {
        if(selected == null) return Optional.empty();
        return Optional.of(selected.getId());
    }

    @Override
    public List<Integer> ready() {
        return ready.stream().map(Task::getId).toList();
    }

    // Subtask 2(a): Complete the implementation of Preemptive Shortest Job First    
    @Override
    public void addTask(Task task) {	

    }

    @Override
    public void schedule() {
        if(selected == null) {                
            selected = ready.poll();          
            if(selected == null) {            
                return;                       
            }                                 
            selected.start();                 
        } else {                              
	    // Subtask 2(a): Complete the implementation of Preemptive Shortest Job First    
	    
        }                                    
    }

}
