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
		if (selected == null)
			return Optional.empty();
		return Optional.of(selected.getId());
	}

	@Override
	public List<Integer> ready() {
		return ready.stream().map(Task::getId).toList();
	}

	// Subtask 2(a): Complete the implementation of Preemptive Shortest Job First
	@Override
	public void addTask(Task task) {
		// Hvis CPU ikke arbeider med noe begynner den med første i køen
		if (selected == null) {
			selected = task;
			selected.start();
			return;
		}

		// Hvis ny prosess Burn-tid er mindre enn den som kjører, flytt den som kjører
		// til readyQue og start ny prosess
		if (task.getRemaining() < selected.getRemaining()) {
			selected.stop();
			ready.add(selected);
			selected = task;
			selected.start();
		} else {
			// Hvis Burn-tid er større enn den som kjører, legg bare til i køen
			ready.add(task);
		}
	}

	@Override
	public void schedule() {
		// Hvis ingen kjører, velg kortest fra køen
		if (selected == null) {
			selected = pickShortestFromQueue();
			if (selected != null) {
				selected.start();
			}
			return;
		}

		// Hvis pågående oppgave er ferdig velg ny med kortest Burst-tid
		if (selected.getRemaining() <= 0) {
			selected = pickShortestFromQueue();
			if (selected != null) {
				selected.start();
			}
		}
	}

	// Hjelpemetode
	private Task pickShortestFromQueue() {
		if (ready.isEmpty()) {
			return null;
		}

		Task shortest = ready.stream().min((a, b) -> Integer.compare(a.getRemaining(), b.getRemaining())).get();
		ready.remove(shortest);
		return shortest;
	}
}