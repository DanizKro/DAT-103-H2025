package HVL.Scheduler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimulationTest {

    Map<Integer, List<Task>> arrivals;
    Simulation simulation;
    private Integer test=1;  // DO NOT change this for Subtasks 1 and 2(a)
                             // ONLY change the value from 1 to 2 for Subtask 2(b).

    @BeforeEach
    public void setUp() {
        simulation = new Simulation();
	switch (test) {
	//Test 1
	case 1:  
	        arrivals = Map.ofEntries(
                Map.entry(0, List.of(
                        simulation.makeTask(5),  
                        simulation.makeTask(3),  
                        simulation.makeTask(1))),
		Map.entry(2, List.of(
                        simulation.makeTask(1),  
                        simulation.makeTask(4))),  
		Map.entry(6, List.of(
                        simulation.makeTask(6),  
                        simulation.makeTask(4))),
                Map.entry(10, List.of(  
                        simulation.makeTask(2))),
		Map.entry(16, List.of(
                        simulation.makeTask(1),  
                        simulation.makeTask(3))));
	    	break;
	//Test 2		
	case 2: 
	        arrivals = Map.ofEntries(
                Map.entry(0, List.of(
                        simulation.makeTask(1),  
                        simulation.makeTask(5),  
                        simulation.makeTask(3))),
		Map.entry(4, List.of(
                        simulation.makeTask(2),  
                        simulation.makeTask(4),  
                        simulation.makeTask(6))),
		Map.entry(12, List.of(
                        simulation.makeTask(5),  
                        simulation.makeTask(2))),
		Map.entry(16, List.of(
                        simulation.makeTask(1),  
                        simulation.makeTask(4))));
		break;
	}
        simulation.setArrivals(arrivals);
    }

    @Test
    public void testRR() {
        var rrScheduler = new RRScheduler(simulation.getClock(),4);  
        simulation.setScheduler(rrScheduler);

	switch (test) {
	case 1:  //Assert Test 1		
        var steps = Stream.generate(() -> {
            simulation.step();
            var state = "T=%d %s".formatted(simulation.time(), rrScheduler.view());
            simulation.clocktick();
            return state;
	    }).limit(31).collect(Collectors.toList());  //31 is the total execution time

        // Subtask 1: Write out expected view for 31 steps of Round Robin scheduling
	// Assert Test 1
        assertThat(steps,contains(
	
        ));
	break;
	
	case 2: break;
	}
    }    

    @Test
    public void testPSJF() {
        var psjfScheduler = new PSJFScheduler();
        simulation.setScheduler(psjfScheduler);

	switch (test) {
	//Assert Test 1		    
	case 1:  
        var steps1 = Stream.generate(() -> {
            simulation.step();
            var state = "T=%d %s".formatted(simulation.time(), psjfScheduler.view());
            simulation.clocktick();
            return state;		    
	    }).limit(31).collect(Collectors.toList());
	
        assertThat(steps1,contains(
                "T=0 Scheduled: T3 Ready: T2, T1",
                "T=1 Scheduled: T2 Ready: T1",
		"T=2 Scheduled: T4 Ready: T2, T5, T1",
		"T=3 Scheduled: T2 Ready: T5, T1",
		"T=4 Scheduled: T2 Ready: T5, T1",
		"T=5 Scheduled: T5 Ready: T1",
		"T=6 Scheduled: T5 Ready: T7, T1, T6",
		"T=7 Scheduled: T5 Ready: T7, T1, T6",
		"T=8 Scheduled: T5 Ready: T7, T1, T6",
		"T=9 Scheduled: T7 Ready: T1, T6",
		"T=10 Scheduled: T8 Ready: T7, T1, T6",
		"T=11 Scheduled: T8 Ready: T7, T1, T6",
		"T=12 Scheduled: T7 Ready: T1, T6",
		"T=13 Scheduled: T7 Ready: T1, T6",
		"T=14 Scheduled: T7 Ready: T1, T6",
		"T=15 Scheduled: T1 Ready: T6",
		"T=16 Scheduled: T9 Ready: T10, T1, T6",
		"T=17 Scheduled: T10 Ready: T1, T6",
		"T=18 Scheduled: T10 Ready: T1, T6",
		"T=19 Scheduled: T10 Ready: T1, T6",
		"T=20 Scheduled: T1 Ready: T6",
		"T=21 Scheduled: T1 Ready: T6",
		"T=22 Scheduled: T1 Ready: T6",
		"T=23 Scheduled: T1 Ready: T6",
		"T=24 Scheduled: T6 Ready: ",
		"T=25 Scheduled: T6 Ready: ",
		"T=26 Scheduled: T6 Ready: ",
		"T=27 Scheduled: T6 Ready: ",
		"T=28 Scheduled: T6 Ready: ",
		"T=29 Scheduled: T6 Ready: ",
                "T=30 Scheduled: Ready: "	
        ));
	break;

	//Assert Test 2	
	case 2:  
        var steps2 = Stream.generate(() -> {
            simulation.step();
            var state = "T=%d %s".formatted(simulation.time(), psjfScheduler.view());
            simulation.clocktick();
            return state;
	    }).limit(34).collect(Collectors.toList());

	// Subtask 2(b): Write out expected view for 34 steps of Preemptive SJF scheduling
	// Assert Test 2
        assertThat(steps2,contains(        
				   
        ));
	break;
	}
    }             
}
