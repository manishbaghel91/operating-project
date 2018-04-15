import java.util.ArrayList;
import java.util.List;

public class TestProcesses {
public static void main(final String[] args) {

final Process p1 = new Process("p1", 0, 20);
final Process p2 = new Process("p2", 5, 36);
final Process p3 = new Process("p3", 13, 19);
final Process p4 = new Process("p4", 17, 42);
final List<Process> processList = new ArrayList<>();
processList.add(p1);
processList.add(p2);
processList.add(p3);
processList.add(p4);

final int no_of_processes = processList.size();

Process first_process = null;
double min = Integer.MAX_VALUE;
for (final Process p : processList) {
if (p.getArr_time() < min) {
min = p.getArr_time();
first_process = p;
}
}

double sum_wait_time = 0;
double sum_turn_around_time = 0;

System.out.println("First process selected for execution is " + first_process.getName());
System.out.println("First process running");
System.out.println("First process ends");

double currTime = first_process.getArr_time();
currTime += first_process.getBurst_time();
sum_turn_around_time += first_process.getBurst_time();
System.out.println("Current time is " + currTime);
System.out.println("Wait time for this process was " + 0);
System.out.println("Turn around time for this process was " + first_process.getBurst_time());
processList.remove(first_process);
double next_arr_time;

Process selected;
while (processList.size() != 0) {
System.out.println();
next_arr_time = getNextArrivalTime(processList);
currTime = Math.max(currTime, next_arr_time);
System.out.println("Current time is " + currTime);
calculatePriorities(currTime, processList);
printProcesses(processList);
selected = getHighestPriorityTask(processList);
System.out.println("Next process selected for execution is " + selected.getName());
System.out.println("Its priority is " + selected.getPriority());
currTime += selected.getBurst_time();
processList.remove(selected);
System.out.println("Current time is " + currTime);
sum_turn_around_time += selected.getBurst_time() + (currTime - selected.getArr_time());
sum_wait_time += (currTime - selected.getArr_time());
System.out.println("Wait time for this process was " + (currTime - selected.getArr_time()));
System.out.println("Turn around time for this process was " + (selected.getBurst_time() + (currTime - selected.getArr_time())));
}

System.out.println();
System.out.println("Average wait time is " + (sum_wait_time / no_of_processes));
System.out.println("Average turn around time is " + (sum_turn_around_time / no_of_processes));

}

public static void calculatePriorities(final double currTime, final List<Process> processList) {
for (final Process p : processList) {
p.setPriority(1 + ((currTime - p.getArr_time()) / p.getBurst_time()));
}
}

public static Process getHighestPriorityTask(final List<Process> processList) {
Process p1 = null;
double max = -1;
for (final Process p : processList) {
if (p.getPriority() > max) {
max = p.getPriority();
p1 = p;
}
}
return p1;
}

public static double getNextArrivalTime(final List<Process> processList) {
double min = Integer.MAX_VALUE;
for (final Process p : processList) {
if (p.getArr_time() < min) {
min = p.getArr_time();
}
}
return min;
}

public static void printProcesses(final List<Process> pList) {
for (final Process p : pList) {
System.out.println(p);
}

}

}

class Process {
String name;
double arr_time;
double burst_time;
double priority;

public Process(final String name, final double arr_time, final double burst_time) {
super();
this.name = name;
this.arr_time = arr_time;
this.burst_time = burst_time;
}

public String getName() {
return this.name;
}

public void setName(final String name) {
this.name = name;
}

public double getArr_time() {
return this.arr_time;
}

public void setArr_time(final double arr_time) {
this.arr_time = arr_time;
}

public double getBurst_time() {
return this.burst_time;
}

public void setBurst_time(final double burst_time) {
this.burst_time = burst_time;
}

public double getPriority() {
return this.priority;
}

public void setPriority(final double priority) {
this.priority = priority;
}

@Override
public String toString() {
return "Process [name=" + this.name + ", arr_time=" + this.arr_time + ", burst_time=" + this.burst_time + ", priority=" + this.priority + "]";
}

}
