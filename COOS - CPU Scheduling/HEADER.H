# include <stdio.h>
# include <conio.h>
# include <alloc.h>
# include <stdlib.h>
# include <dos.h>

struct IO_Opn
{
	int io_arrival;
	int io_burst;
	int io_time_left;
	struct IO_Opn *next;
};
/****************************************************************************/
struct Process
{
	int process_id;
	int arrival_time,service_time;
	int no_io_opns;
	int time_left;
	int tat;
	struct IO_Opn *io_head;
	struct Process *next;
};
/****************************************************************************/
//constants
const int false = 0;
const int true = 1;
const int RANDOMISE = 1;
/****************************************************************************/
int no_io_opns;
int arrival_range;
int io_interrupt;
int timeslice = -1;
int no_of_processes;
struct Process *readyQhead, *blockedQhead, *opQhead, *ipQhead;
/****************************************************************************/
void freeMemory();
int printSimulationOptions();
void compareResults();
void add_to_ipQ(struct Process *);
int rand_num(int,int);
void setID();
void printProcessesInfo();
void printSimulation(struct Process *,int,int,int);
void saveInputToFile();
struct Process* loadInputFromFile();
void add_to_opQ (struct Process *);
void add_to_blockedQ (struct Process *);
void add_to_readyQ (struct Process *);
void askInputOptions();
void schedule(int);
void generateProcesses();
void first_come_first_serve(int);
void shortest_process_next(int);
void shortest_remaining_time(int);
void round_robin(int,int);
/****************************************************************************/




