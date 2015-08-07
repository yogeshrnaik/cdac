# include "header.h"

int main()
{
	int option;
	askInputOptions();
	while(1)
	{
		clrscr();
		printf("\n\t\t       ====================================");
		printf("\n\t\t       CPU SCHEDULING ALGORITHMS SIMULATION");
		printf("\n\t\t       ====================================");
		printf("\n\n\t\t\t1 : First Come First Serve (Non-Preemptive)\n");
		printf("\n\t\t\t2 : Shortest Process Next (Non-Preemptive)\n");
		printf("\n\t\t\t3 : Shortest Remaining Time (Preemptive)\n");
		printf("\n\t\t\t4 : Round Robin (Preemptive)\n");
		printf("\n\t\t\t5 : View input processes\n");
		printf("\n\t\t\t6 : Change input specifications\n");
		printf("\n\t\t\t7 : Generate New Set of Input Processes\n");
		printf("\n\t\t\t8 : Compare all results\n");
		printf("\n\t\t\t9 : Quit\n");
		printf("\n\n\t\t\tChoose the appropriate option : ");
		scanf("%d",&option);
		schedule(option);
	}
	return 0;
}
/****************************************************************************/
void freeMemory()
{
	struct Process *currProcess, *tempProcess;
	struct IO_Opn  *curr_io, *temp_io;
	// free up opQ
	currProcess = opQhead;
	while (currProcess!=NULL)
	{
		tempProcess = currProcess->next;
		free (currProcess);
		currProcess = tempProcess;
	}
	// free up ipQ
	currProcess = ipQhead;
	while (currProcess!=NULL)
	{
		tempProcess = currProcess->next;
		curr_io = currProcess->io_head;
		while (curr_io!=NULL)
		{
			temp_io = curr_io->next;
			free (curr_io);
			curr_io = temp_io;
		}
		free (currProcess);
		currProcess = tempProcess;
	}
	ipQhead = NULL;
	opQhead = NULL;

}
/****************************************************************************/
int printSimulationOptions()
{
	int option;
	printf("\n\n\t\t\t1 : See results with simulation");
	printf("\n\n\t\t\t2 : See results without simulation");
	printf("\n\n\n\t\t\tEnter your choice : ");
	scanf("%d",&option);
	printf("\n\n");
	clrscr();
	return(option);
}
/****************************************************************************/
void compareResults()
{
	FILE *fp;
	int i;
	int temp1;
	double temp2;
	char * algofile[] = {
							"FCFS.TXT",
							"SPN.TXT",
							"SRT.TXT",
							"RR.TXT"
						};
	char * algoname[] = {
							"FCFS",
							"SPN",
							"SRT",
							"RR"
						};

	ipQhead = loadInputFromFile();
	opQhead = NULL;
	first_come_first_serve(2);

	ipQhead = loadInputFromFile();
	opQhead = NULL;
	shortest_process_next(2);

	ipQhead = loadInputFromFile();
	opQhead = NULL;
	shortest_remaining_time(2);

	ipQhead = loadInputFromFile();
	opQhead = NULL;
	round_robin(2,3);

	clrscr();
	printf("\n\n\t\t\tCOMPARISON OF ALL RESULTS");
	printf("\n\t\t\t=========================");
	printf("\n\n\tTotal T     Avg.TAT    Throughput    CPU Util    CPU Util(Productive)");

	for (i=0;i<4;i++)
	{
		int j;
		fp = fopen(algofile[i],"r");

		printf("\n%s",algoname[i]);

		fscanf(fp,"%d",&temp1);
		printf("\t%5d",temp1);

		fscanf(fp,"%lf",&temp2);
		printf("\t%10.2lf",temp2);

		fscanf(fp,"%lf",&temp2);
		printf("\t%3.2lf %",temp2);

		fscanf(fp,"%lf",&temp2);
		printf("\t%11.2lf %",temp2);

		fscanf(fp,"%lf",&temp2);
		printf("\t%12.2lf %",temp2);
		fclose(fp);

	}

}
/****************************************************************************/
void add_to_ipQ(struct Process *newProcess)
{
	struct Process *curr,*prev;
	if (ipQhead == NULL)
	{
		ipQhead = newProcess;
		newProcess->next = NULL;
		return;
	}

	curr = ipQhead;
	prev = ipQhead;
	while (newProcess->arrival_time > curr->arrival_time && curr!=NULL)
	{
		prev = curr;
		curr = curr->next;
	}
	if (curr == NULL)
		prev->next = newProcess;
	else
	{
		newProcess->next = curr;
		prev->next = newProcess;
	}
}
/****************************************************************************/
int rand_num(int start, int end)
{
	int temp=-1;
	while (temp < start)
		temp = random(end+1);
	return(temp);
}
/****************************************************************************/
void setID()
{
	int i=1;
	struct Process *curr;
	for (curr=ipQhead; curr!=NULL; curr=curr->next)
	{
		curr->process_id = i++;
		curr->time_left = curr->service_time;
	}
}
/****************************************************************************/
void printProcessesInfo()
{
	int count=0, i;
	struct IO_Opn *temp_io;
	struct Process *head;
	clrscr();
	head = ipQhead;
	printf("P_ID Arr_time Ser_time I/O opns I/O Arr I/O burst\n");
	if (head!=NULL)
	{
		for (;head!=NULL;head=head->next)
		{
			printf("%2d",head->process_id);
			printf("%7d",head->arrival_time);
			printf("%10d",head->service_time);
			printf("%10d\n",head->no_io_opns);
			for (temp_io=head->io_head;temp_io!=NULL;temp_io=temp_io->next)
			{
				printf("%38d",temp_io->io_arrival);
				printf("%8d\n",temp_io->io_burst);
			}
			count++;
		}
		printf("\nTotal number of processes : %d",count);
	}
	getch();
}
/****************************************************************************/
void printResults(FILE *fp,int timecount,int cputime,int no_of_switches)
{
	struct Process *p;
	double ntat,throughput,cpu_util, avg_tat, total_tat=0;
	int cpu_productive;

	// time for which the cpu was executing user processes
	cpu_productive = cputime;

	// time for which cpu was executing either user processes or the OS Code
	cputime += (int)(0.1 * no_of_switches);
	// total time of simulation
	timecount += (int)(0.1 * no_of_switches);
	/* cpu utilisation includes the time for which cpu executes
	either user processes or the OS Code for process switching */
	cpu_util = (cputime / (timecount*1.0)) * 100;

	printf("\n ID\tService Time\tTAT\tNormalised TAT");
	for (p=opQhead;p!=NULL;p=p->next)
	{
		ntat = p->tat / (p->service_time*1.0);
		total_tat += p->tat;
		printf("\n%3d%11d%13d%16lf",p->process_id,p->service_time,p->tat,ntat);
	}
	avg_tat = total_tat/(1.0*no_of_processes);
	throughput = no_of_processes / (timecount*1.0);
	printf("\n\nTotal time      	     : %d",timecount);
	printf("\nAverage Turnaround Time      : %3.2lf",avg_tat);
	printf("\nThroughput      	     : %3.2lf %",throughput*100);
	printf("\nCPU Utilisation              : %3.2lf %",cpu_util);
	printf("\nCPU Utilisation (Productive) : %3.2lf %",(cpu_productive / (1.0*timecount)) * 100);

	fprintf(fp,"%d",timecount);
	fprintf(fp,"\n%.2lf",avg_tat);
	fprintf(fp,"\n%.2lf",throughput*100);
	fprintf(fp,"\n%.2lf",cpu_util);
	fprintf(fp,"\n%.2lf",(cpu_productive / (1.0*timecount)) * 100);
	getch();
	getch();
}
/****************************************************************************/
void printSimulation(struct Process *curr,int timecount,int cputime, int no_of_switches)
{
	struct IO_Opn *temp_io;
	struct Process *temp;
	clrscr();
	printf("Input Queue:\n");
	printf("============\n");
	temp = ipQhead;
	while (temp!=NULL)
	{
		printf("pid = %02d",temp->process_id);
		printf(" Arrival time = %02d",temp->arrival_time);
		printf(" Service time = %02d",temp->service_time);
		printf("  %d IO opn(s):",temp->no_io_opns);
		if (temp->io_head!=NULL)
		{
			for (temp_io=temp->io_head;temp_io!=NULL;temp_io=temp_io->next)
			{
				printf(" %d",temp_io->io_arrival);
				printf(",%d ",temp_io->io_burst);
			}
		}
		temp = temp->next;
		printf("\n");
	}

	temp = readyQhead;
	printf("\nReady Queue:\n");
	printf("============\n");
	while (temp!=NULL)
	{
		printf("pid = %02d",temp->process_id);
		printf(" Arrival time = %02d",temp->arrival_time);
		printf(" Time left = %02d",temp->time_left);
		printf("  %d IO opn(s):",temp->no_io_opns);
		if (temp->io_head!=NULL)
		{
			for (temp_io=temp->io_head;temp_io!=NULL;temp_io=temp_io->next)
			{
				printf(" %d",temp_io->io_time_left);
				printf(",%d ",temp_io->io_burst);
			}
		}
		printf(" T = %d",timecount-temp->arrival_time);
		if (curr == temp)
			printf("  <---");
		temp = temp->next;
		printf("\n");
	}

	temp = blockedQhead;
	printf("\nBlocked Queue:\n");
	printf("==============\n");
	while (temp!=NULL)
	{
		printf("pid = %02d",temp->process_id);
		printf(" Arrival time = %02d",temp->arrival_time);
		printf(" Time left = %02d",temp->time_left);
		printf("  %d IO opn(s):",temp->no_io_opns);
		if (temp->io_head!=NULL)
		{
			for (temp_io=temp->io_head;temp_io!=NULL;temp_io=temp_io->next)
			{
				printf(" %d",temp_io->io_time_left);
				printf(",%d ",temp_io->io_burst);
			}
		}

		printf(" T = %d",timecount-temp->arrival_time);

		temp = temp->next;
		printf("\n");
	}

	temp = opQhead;
	printf("\nOutput Queue:\n");
	printf("=============\n");
	while (temp!=NULL)
	{
		printf("pid = %02d",temp->process_id);
		printf("\t\tTurn Around Time = %02d",temp->tat);
		temp = temp->next;
		printf("\n");
	}

	printf("\nTime = %02d",timecount);
	printf("\tCPU Time = %02d",cputime);
	if (timeslice != -1)
		printf("\tTimeslice = %02d",timeslice);
	printf("\tNo. of Switches = %02d", no_of_switches);
	printf("\n\nPress any key to continue OR q to quit");
//	sleep(1);
}
/****************************************************************************/
void saveInputToFile()
{
	FILE *fp;
	struct IO_Opn *temp_io;
	struct Process *head;
	head = ipQhead;

	fp = fopen("mainQ.dat","w");
	fprintf(fp,"%d\n",no_of_processes);
	while (head != NULL)
	{
		fprintf(fp,"%d ",head->process_id);
		fprintf(fp,"%d ",head->arrival_time);
		fprintf(fp,"%d ",head->service_time);
		fprintf(fp,"%d ",head->no_io_opns);
		temp_io = head->io_head;
		while (temp_io != NULL)
		{
			fprintf(fp,"%d ",temp_io->io_arrival);
			fprintf(fp,"%d ",temp_io->io_burst);
			temp_io = temp_io->next;
		}
		fprintf(fp,"\n");
		head = head->next;
	}
	fclose(fp);
}
/****************************************************************************/
struct Process* loadInputFromFile()
{
	FILE *fp;
	struct Process *head,*temp;
	struct IO_Opn *temp_io;
	int i,n,j;

	fp = fopen("mainQ.dat","r");
	fscanf(fp,"%d\n",&n);

	head = NULL;
	temp = NULL;

	for (i=1;i<=n;i++)
	{
		temp = (struct Process *)malloc (sizeof (struct Process));
		temp->next = NULL;
		temp->io_head = NULL;
		fscanf(fp,"\n%d ",&(temp->process_id));
		fscanf(fp,"%d ",&(temp->arrival_time));
		fscanf(fp,"%d ",&(temp->service_time));
		fscanf(fp,"%d ",&(temp->no_io_opns));
		temp->time_left = temp->service_time;
		for (j=1;j<=temp->no_io_opns;j++)
		{

			temp_io = (struct IO_Opn *)malloc (sizeof (struct IO_Opn));
			temp_io->next = NULL;
			fscanf(fp,"%d ",&(temp_io->io_arrival));
			fscanf(fp,"%d ",&(temp_io->io_burst));
			temp_io->io_time_left = temp_io->io_arrival;
			if (temp->io_head == NULL)
				temp->io_head = temp_io;
			else
				temp->io_head->next = temp_io;
		}

		if (head == NULL)
			head = temp;
		else
		{
			struct Process *curr;
			curr = head;
			while (curr->next!=NULL)
				curr = curr->next;
			curr->next = temp;
		}
	}
	fclose(fp);
	return(head);
}
/****************************************************************************/
void add_to_opQ (struct Process *newProcess)
{
	if (opQhead == NULL)
	{
		opQhead = newProcess;
		newProcess->next = NULL;
	}
	else
	{
		struct Process *curr = opQhead;
		for (; curr->next!=NULL; curr=curr->next);
		curr->next = newProcess;
		newProcess->next = NULL;
	}
}
/****************************************************************************/
void add_to_blockedQ (struct Process *newProcess)
{
	if (blockedQhead == NULL)
	{
		blockedQhead = newProcess;
		newProcess->next = NULL;
	}
	else
	{
		struct Process *curr = blockedQhead;
		for (; curr->next!=NULL; curr=curr->next);
		curr->next = newProcess;
		newProcess->next = NULL;
		curr = NULL;
	}
}
/****************************************************************************/
void add_to_readyQ (struct Process *newProcess)
{
	if (readyQhead == NULL)
	{
		readyQhead = newProcess;
		newProcess->next = NULL;
	}
	else
	{
		struct Process *curr = readyQhead;
		for (; curr->next!=NULL; curr=curr->next);
		curr->next = newProcess;
		newProcess->next = NULL;
	}
}
/****************************************************************************//****************************************************************************//****************************************************************************/
void first_come_first_serve(int choice)
{
	int timecount = 0;
	int cputime   = 0;
	int no_of_switches = 0;
	int process_switch = 0;
	FILE *fp;

	io_interrupt  = 0;
	readyQhead    = NULL;
	blockedQhead  = NULL;
	if (choice==1)
	{
		printSimulation(readyQhead,timecount,cputime, no_of_switches);
		if (getch() == 'q' || getch() == 'Q')
			choice = 0;
	}
	while (ipQhead!=NULL || readyQhead!=NULL || blockedQhead!=NULL)
	{
		/* check for the interrupt before proceeding to
		   next instruction cycle */
		if (io_interrupt)
		{
			struct Process *p;
			struct IO_Opn *temp_io;

			p = blockedQhead->next;
			blockedQhead->no_io_opns--;
			temp_io = blockedQhead->io_head;
			blockedQhead->io_head = blockedQhead->io_head->next;
			free (temp_io);

			// if process needs CPU TIME, add it to ready queue
			if (blockedQhead->time_left != 0)
				add_to_readyQ(blockedQhead);
			// else add it to output queue
			else
			{
				blockedQhead->tat = timecount - blockedQhead->arrival_time;
				add_to_opQ(blockedQhead);
			}
			// set interrupt to zero indicating that IO Device is free now
			io_interrupt = 0;

			// remove process from blocked queue
			blockedQhead = p;
			process_switch = 1;
		}
		// if process completes execution or requests io operation
		else if (readyQhead != NULL)
		{
			if (readyQhead->time_left == 0)
			{
				struct Process *p;
				readyQhead->tat = timecount - readyQhead->arrival_time;
				p = readyQhead->next;
				add_to_opQ(readyQhead);
				readyQhead = p;
				process_switch = 1;
			}
			// io request
			else if (readyQhead->io_head != NULL)
			{
//				if (readyQhead->service_time - readyQhead->time_left == readyQhead->io_head->io_arrival)
				if (readyQhead->io_head->io_time_left == 0)
				{
					struct Process *p;
					p = readyQhead->next;
					add_to_blockedQ(readyQhead);
					readyQhead = p;
					process_switch = 1;
				}
			}
		}
		// if new process arrives
		if (!process_switch && ipQhead != NULL)
			if (timecount == ipQhead->arrival_time)
				{
					struct Process *p;
					p = ipQhead->next;
					add_to_readyQ(ipQhead);
					ipQhead = p;
					process_switch = 1;
				}

		if (process_switch)
		{
			no_of_switches++;
			process_switch = 0;
			if (choice==1)
			{
				printSimulation(readyQhead,timecount,cputime, no_of_switches);
				if (getch() == 'q' || getch() == 'Q')
					choice = 0;
			}
			continue;
		}
		/* if io interrupt has not occured, reduce the
		time left for io_operation completion */
		if (blockedQhead != NULL)
		{
			blockedQhead->time_left--;
			if (blockedQhead->io_head->next!=NULL)
				blockedQhead->io_head->next->io_time_left--;
			blockedQhead->io_head->io_burst--;
			/* check whether io operation is completed
			if yes set io_interrupt to one indicating that
			process has completed io operation and
			needs cpu attention */
			if (blockedQhead->io_head->io_burst == 0)
				io_interrupt = 1;
		}
		if (readyQhead!=NULL)
		{
			readyQhead->time_left--;
			if (readyQhead->io_head != NULL)
			{
				readyQhead->io_head->io_time_left--;
				if (readyQhead->io_head->next != NULL)
					readyQhead->io_head->next->io_time_left--;
			}
			cputime++;
		}
		timecount++;
		if (choice==1)
		{
			printSimulation(readyQhead,timecount,cputime, no_of_switches);
			if (getch() == 'q' || getch() == 'Q')
				choice = 0;
		}
	} // while
	clrscr();
//	printf("======================\n");
	printf("First Come First Serve\n");
	printf("======================\n");

	fp = fopen("FCFS.TXT","w");
	printResults(fp,timecount,cputime,no_of_switches);
	fclose(fp);
	freeMemory();
} // fcfs
/****************************************************************************/
void shortest_process_next(int choice)
{
	int timecount = 0;
	int cputime = 0;
	struct Process *curr,*prev;
	int process_switch = 0;
	int no_of_switches = 0;
	FILE *fp;

	io_interrupt = 0;
	readyQhead   = NULL;
	blockedQhead = NULL;
	curr = NULL;
	prev = NULL;

	if (choice==1)
	{
		printSimulation(readyQhead,timecount,cputime, no_of_switches);
		if (getch() == 'q' || getch() == 'Q')
			choice = 0;
	}

	while (ipQhead!=NULL || readyQhead!=NULL || blockedQhead!=NULL)
	{
		// check for io interrupt
		if (io_interrupt)
		{
			struct Process *p;
			struct IO_Opn *temp_io;

			p = blockedQhead->next;
			blockedQhead->no_io_opns--;
			temp_io = blockedQhead->io_head;
			blockedQhead->io_head = blockedQhead->io_head->next;
			free (temp_io);

			if (blockedQhead->time_left != 0)
				add_to_readyQ(blockedQhead);
			else
			{
				blockedQhead->tat = timecount - blockedQhead->arrival_time;
				add_to_opQ(blockedQhead);
			}
			io_interrupt = 0;
			blockedQhead = p;
			process_switch = 1;
		}
		// check if currently executing process finishes or requests io
		else if (curr != NULL)
		{
			// check whether currently executing process finishes
			if (curr->time_left == 0)
			{
				struct Process *p;
				curr->tat = timecount - curr->arrival_time;

				// remove that process from readyQ
				if (curr == readyQhead)	// if process is at the readyQhead
				{
					p = readyQhead->next;
					add_to_opQ(readyQhead);
					readyQhead = p;
				}
				else
				{
					p = curr->next;
					add_to_opQ(curr);
					prev->next = p;
				}
				// set currently executing process to null
				// indicating that processor is now idle
				curr = NULL;
				process_switch = 1;
			}
			// if curr process is not finished check whether it requests IO
			else if (curr->io_head != NULL)
			{
				if (curr->service_time - curr->time_left == curr->io_head->io_arrival)
				{
					struct Process *p;
					// add process to blockedQ
					if (curr == readyQhead)
					{
						p = readyQhead->next;
						add_to_blockedQ(readyQhead);
						readyQhead = p;
					}
					else
					{
						p = curr->next;
						add_to_blockedQ(curr);
						prev->next = p;
					}
					curr = NULL;
					process_switch = 1;
				}
			}
		} // if (curr ! NULL)

		// if new process arrives
		if (!process_switch && ipQhead != NULL)
			if (timecount == ipQhead->arrival_time)
			{
				struct Process *p;
				p = ipQhead->next;
				add_to_readyQ(ipQhead);
				ipQhead = p;
				process_switch = 1;
			}

		if (process_switch)
		{
			no_of_switches++;
			process_switch = 0;
			if (choice==1)
			{
				printSimulation(curr,timecount,cputime, no_of_switches);
				if (getch() == 'q' || getch() == 'Q')
					choice = 0;
			}
			continue;
		}
		// check for shortest process next if processor is idle
		if (curr == NULL && readyQhead != NULL)
		{
			struct Process *temp;
			int min = 100;
			temp = readyQhead;
			prev = NULL;
			// find out process with minimum time left or service time
			while (temp!=NULL)
			{
				if (temp->time_left < min)
				{
					min = temp->time_left;
					curr = temp;
				}
				temp = temp->next;
			}
			// get the pointer to prev process (prev to min)
			// for manipulations
			temp = readyQhead;
			while (temp->next!=NULL)
			{
				if (temp->next == curr)
					prev = temp;
				temp = temp->next;
			}
			// special case ie if min process is at the readyQhead
			if (prev == NULL)
				prev = curr;
		} // if (!process_switch && curr == NULL)

		// execute io operation of the process that is in the blockedQhead
		if (blockedQhead != NULL)
		{
			blockedQhead->time_left--;
			if (blockedQhead->io_head->next!=NULL)
				blockedQhead->io_head->next->io_time_left--;
			blockedQhead->io_head->io_burst--;
			if (blockedQhead->io_head->io_burst == 0)
				io_interrupt = 1;
		}

		// execute the currently available process
		if (curr != NULL)
		{
			curr->time_left--;
			if (curr->io_head != NULL)
			{
				curr->io_head->io_time_left--;
				if (curr->io_head->next != NULL)
					curr->io_head->next->io_time_left--;
			}
			cputime++;
		}
		timecount++;
		if (choice==1)
		{
			printSimulation(curr,timecount,cputime, no_of_switches);
			if (getch() == 'q' || getch() == 'Q')
				choice = 0;
		}
	} // while
	clrscr();
	printf("Shortest Process Next\n");
	printf("=====================\n");
	fp = fopen("SPN.TXT","w");
	printResults(fp,timecount,cputime,no_of_switches);

	fclose(fp);
	freeMemory();
} // spn
/****************************************************************************/
void shortest_remaining_time(int choice)
{
	int timecount = 0;
	int cputime = 0;
	struct Process *curr,*prev;
	int process_switch = 0;
	int no_of_switches = 0;
	int readyQ_updated = 0;
	FILE *fp;

	io_interrupt = 0;
	readyQhead   = NULL;
	blockedQhead = NULL;
	curr = NULL;
	prev = NULL;
	if (choice==1)
	{
		printSimulation(curr,timecount,cputime, no_of_switches);
		if (getch() == 'q' || getch() == 'Q')
			choice = 0;
	}

	while (ipQhead!=NULL || readyQhead!=NULL || blockedQhead!=NULL)
	{
		// check for io interrupt
		if (io_interrupt)
		{
			struct Process *p;
			struct IO_Opn *temp_io;

			p = blockedQhead->next;
			blockedQhead->no_io_opns--;
			temp_io = blockedQhead->io_head;
			blockedQhead->io_head = blockedQhead->io_head->next;
			free (temp_io);

			// add process to readyQ
			if (blockedQhead->time_left != 0)
			{
				add_to_readyQ(blockedQhead);
				readyQ_updated = 1;
			}
			else
			{
				blockedQhead->tat = timecount - blockedQhead->arrival_time;
				add_to_opQ(blockedQhead);
			}
			io_interrupt = 0;
			blockedQhead = p;
			process_switch = 1;
		}
		// check whether process finishes
		else if (curr != NULL)
		{
			if (curr->time_left == 0)
			{
				struct Process *p;
				curr->tat = timecount - curr->arrival_time;
				if (curr == readyQhead)
				{
					p = readyQhead->next;
					add_to_opQ(readyQhead);
					readyQhead = p;
				}
				else
				{
					p = curr->next;
					add_to_opQ(curr);
					prev->next = p;
				}
				curr = NULL;
				process_switch = 1;
				readyQ_updated = 1;
			}
			// check whether process requests IO
			else if (curr->io_head != NULL)
			{
				if (curr->io_head->io_time_left == 0)
				{
					struct Process *p;
					if (curr == readyQhead)
					{
						p = readyQhead->next;
						add_to_blockedQ(readyQhead);
						readyQhead = p;
					}
					else
					{
						p = curr->next;
						add_to_blockedQ(curr);
						prev->next = p;
						curr = NULL;
					}
					curr = NULL;
					process_switch = 1;
					readyQ_updated = 1;
				}
			}
		} // if (curr != NULL)

		// if new process arrives in the system
		if (!process_switch && ipQhead != NULL)
			if (timecount == ipQhead->arrival_time)
			{
				struct Process *p;
				p = ipQhead->next;
				add_to_readyQ(ipQhead);
				ipQhead = p;
				process_switch = 1;
				readyQ_updated = 1;
			}

		if (process_switch)
		{
			no_of_switches++;
			process_switch = 0;
			if (choice==1)
			{
				printSimulation(curr,timecount,cputime, no_of_switches);
				if (getch() == 'q' || getch() == 'Q')
					choice = 0;
			}
			continue;
		}
		if (readyQ_updated && readyQhead != NULL)
		{
			struct Process *temp;
			int min = 100;
			readyQ_updated = 0;
			temp = readyQhead;
			prev = NULL;
			// find the next shortest process
			// (add this) only if readyQ is updated
			while (temp!=NULL)
			{
				if (temp->time_left < min)
				{
					min = temp->time_left;
					curr = temp;
				}
				temp = temp->next;
			}
			// get the pointer to prev process (prev to min)
			// for manipulations
			temp = readyQhead;
			while (temp->next!=NULL)
			{
				if (temp->next == curr)
					prev = temp;
				temp = temp->next;
			}
			// special case ie if shortest process is at the readyQhead
			if (prev == NULL)
				prev = curr;
		} // if (readyQ_updated)


		// execute io operation of the blockedQhead process
		if (blockedQhead != NULL)
		{
			blockedQhead->time_left--;
			if (blockedQhead->io_head->next!=NULL)
				blockedQhead->io_head->next->io_time_left--;
			blockedQhead->io_head->io_burst--;
			if (blockedQhead->io_head->io_burst == 0)
				io_interrupt = 1;
		}
		// execute the current running process
		if (curr!=NULL)
		{
			curr->time_left--;
			if (curr->io_head != NULL)
			{
				curr->io_head->io_time_left--;
				if (curr->io_head->next != NULL)
					curr->io_head->next->io_time_left--;
			}
			cputime++;
		}
		timecount++;
		if (choice==1)
		{
			printSimulation(curr,timecount,cputime,no_of_switches);
			if (getch() == 'q' || getch() == 'Q')
				choice = 0;
		}
	} // while
	clrscr();
	printf("Shortest Remaining Time\n");
	printf("=======================\n");

	fp = fopen("SRT.TXT","w");
	printResults(fp,timecount,cputime,no_of_switches);
	fclose(fp);
	freeMemory();
} // srt
/****************************************************************************/
void round_robin(int choice, int QUANTUM)
{
	int timecount = 0;
	int cputime = 0;
	int process_switch = 0;
	int no_of_switches = 0;
	FILE *fp;

	io_interrupt = 0;
	timeslice = 0;
	readyQhead   = NULL;
	blockedQhead = NULL;
	if (choice==1)
	{
		printSimulation(readyQhead,timecount,cputime,no_of_switches);
		if (getch() == 'q' || getch() == 'Q')
			choice = 0;
	}
	while (ipQhead!=NULL || readyQhead!=NULL || blockedQhead!=NULL)
	{
		// check currently executing process finishes or requests io
		if (readyQhead != NULL)
		{
			// if process finishes, add it to opQ
			if (readyQhead->time_left == 0)
			{
				struct Process *p;
				readyQhead->tat = timecount - readyQhead->arrival_time;
				p = readyQhead->next;
				add_to_opQ(readyQhead);
				readyQhead = p;
				process_switch = 1;
				timeslice = 0;
			}
			// io request
			else if (readyQhead->io_head != NULL)
			{
				// add to blockedQ
				if (readyQhead->io_head->io_time_left == 0)
				{
					struct Process *p;
					p = readyQhead->next;
					add_to_blockedQ(readyQhead);
					readyQhead = p;
					process_switch = 1;
					timeslice = 0;
				}
			}
		}
		// check for io interrupt
		if (!process_switch && io_interrupt)
		{
			struct Process *p;
			struct IO_Opn *temp_io;

			p = blockedQhead->next;
			blockedQhead->no_io_opns--;
			temp_io = blockedQhead->io_head;
			blockedQhead->io_head = blockedQhead->io_head->next;
			free (temp_io);


			if (blockedQhead->time_left != 0)
				add_to_readyQ(blockedQhead);
			else
			{
				blockedQhead->tat = timecount - blockedQhead->arrival_time;
				add_to_opQ(blockedQhead);
			}
			io_interrupt = 0;
			blockedQhead = p;
			process_switch = 1;
		}

		// check if new process arrives
		if (!process_switch && ipQhead != NULL)
			if (timecount == ipQhead->arrival_time)
			{
				struct Process *p;
				p = ipQhead->next;
				add_to_readyQ(ipQhead);
				ipQhead = p;
				process_switch = 1;
			}

		// check if timeslice is over
		if (!process_switch && timeslice == QUANTUM)
		{
			process_switch = 1;
			timeslice = 0;
			// add the process to the end of readyQ
			if (readyQhead->next != NULL) // more than 1 processes in readyQ
			{
				struct Process *curr, *old_readyQhead;
				curr = readyQhead;
				old_readyQhead = readyQhead;
				for (; curr->next!=NULL; curr=curr->next);
				curr->next = readyQhead;
				readyQhead = readyQhead->next;
				old_readyQhead->next = NULL;
			}
		} // timeslice over

		if (process_switch)
		{
			no_of_switches++;
			process_switch = 0;
			if (choice==1)
			{
				printSimulation(readyQhead,timecount,cputime, no_of_switches);
				if (getch() == 'q' || getch() == 'Q')
					choice = 0;
			}
			continue;
		}

		// execute io of blockedQhead process
		if (blockedQhead != NULL)
		{
			blockedQhead->time_left--;
			if (blockedQhead->io_head->next!=NULL)
				blockedQhead->io_head->next->io_time_left--;
			blockedQhead->io_head->io_burst--;
			if (blockedQhead->io_head->io_burst == 0)
				io_interrupt = 1;
		}
		// execute the process at the readyQhead
		if (readyQhead!=NULL)
		{
			readyQhead->time_left--;
			if (readyQhead->io_head != NULL)
			{
				readyQhead->io_head->io_time_left--;
				if (readyQhead->io_head->next != NULL)
					readyQhead->io_head->next->io_time_left--;
			}
			cputime++;
			timeslice++;
		}
		timecount++;
		if (choice==1)
		{
			printSimulation(readyQhead,timecount,cputime,no_of_switches);
			if (getch() == 'q' || getch() == 'Q')
				choice = 0;
		}
	} // while

	clrscr();
	printf("Round robin\n");
	printf("===========\n");

	fp = fopen("RR.TXT","w");
	printResults(fp,timecount,cputime,no_of_switches);
	fclose(fp);
	freeMemory();
} // rr
/****************************************************************************/
void schedule(int algorithm)
{
	int i;

	freeMemory();

	ipQhead = loadInputFromFile();
	opQhead = NULL;

	switch(algorithm)
	{
		case 1:
		{
			clrscr();
			printf("\n\n\t\t\t======================");
			printf("\n\t\t\tFirst Come First Serve");
			printf("\n\t\t\t======================");
			i = printSimulationOptions();
			timeslice = -1;
			first_come_first_serve(i);
//			getch();
			getch();
			break;
		}

		case 2:
		{
			clrscr();
			printf("\n\n\t\t\t=====================");
			printf("\n\t\t\tShortest Process Next");
			printf("\n\t\t\t=====================");
			i = printSimulationOptions();
			timeslice = -1;
			shortest_process_next(i);
			getch();
			getch();
			break;
		}
		case 3:
		{
			clrscr();
			printf("\n\n\t\t\t=======================");
			printf("\n\t\t\tShortest Remaining Time");
			printf("\n\t\t\t=======================");
			i = printSimulationOptions();
			timeslice = -1;
			shortest_remaining_time(i);
			getch();
			getch();
			break;
		}
		case 4:
		{
			int quantum = 0;
			clrscr();
			printf("\n\n\t\t\t\t===========");
			printf("\n\t\t\t\tRound Robin");
			printf("\n\t\t\t\t===========");
			printf("\n\n\n\t\t\tEnter the value of quantum : ");
			scanf("%d",&quantum);
			i = printSimulationOptions();
			round_robin(i,quantum);
			getch();
			getch();
			break;
		}
		case 5 :
		{
			printProcessesInfo();
			getch();
			break;
		}
		case 6 :
		{
			askInputOptions();
			break;
		}
		case 7 :
		{
			generateProcesses();
			break;
		}
		case 8 :
		{
			clrscr();
			compareResults();
			getch();
			break;
		}
		default : exit(1);
	}
}
/****************************************************************************/
void generateProcesses ()
{
	int i,j;
	int times[101];
	int done;
	struct Process *temp;
	struct IO_Opn *temp_io;

	// before generating new processes, free up memory
	freeMemory();

	readyQhead = NULL;
	blockedQhead = NULL;
	opQhead = NULL;
	ipQhead = NULL;

	temp = NULL;
	temp_io = NULL;

	for (i=0;i<no_of_processes;i++)
		times[i] = -1;
	if (RANDOMISE == 1)
		randomize();
	for (i=1;i<=no_of_processes;i++)
	{
		temp = (struct Process *) malloc(sizeof(struct Process));
		temp->io_head = NULL;
		temp->arrival_time = rand_num(0,arrival_range);
		done = false;
		while (!done && i!=1)
		{
			for (j=1;j<i;j++)
				if (times[j] == temp->arrival_time)
				{
					done = false;
					temp->arrival_time = rand_num(0, arrival_range);
					break;
				}
			else
				done = true;
		}
		times[i] = temp->arrival_time;

		temp->no_io_opns = rand_num(0,no_io_opns);

		if (temp->no_io_opns >= 1)
		{
			temp_io = (struct IO_Opn *)malloc(sizeof(struct IO_Opn));
			temp_io->io_burst = rand_num(1,7);
			temp_io->io_arrival = rand_num(1,5);
			temp_io->io_time_left = temp_io->io_arrival;
			temp_io->next = NULL;
			temp->io_head = temp_io;
		}

		if (temp->no_io_opns == 2)
		{
			temp_io = (struct IO_Opn *)malloc(sizeof(struct IO_Opn));
			temp_io->next = NULL;
			temp_io->io_burst = rand_num(1,7);
			temp_io->io_arrival = rand_num(temp->io_head->io_arrival + temp->io_head->io_burst + 2,temp->io_head->io_arrival + temp->io_head->io_burst + 3);
			temp_io->io_time_left = temp_io->io_arrival;
			temp->io_head->next = temp_io;
		}

		if (temp->io_head == NULL)
			temp->service_time = rand_num(1,20);
		else
		{
			for (temp_io=temp->io_head;temp_io->next!=NULL;temp_io=temp_io->next);
			temp->service_time = temp_io->io_arrival + temp_io->io_burst + rand_num(1,6);
		}

		temp->next = NULL;
		if (ipQhead == NULL)
			ipQhead = temp;
		else if (temp->arrival_time < ipQhead->arrival_time)
		{
			temp->next = ipQhead;
			ipQhead = temp;
		}
		else
			add_to_ipQ(temp);
	}
	setID();
	saveInputToFile();
	printProcessesInfo();
	getch();
}
/****************************************************************************/
void askInputOptions()
{
	int option;
	clrscr();
	printf("\n\n\t\t      ====================================");
	printf("\n\t\t      CPU SCHEDULING ALGORITHMS SIMULATION");
	printf("\n\t\t      ====================================");
	printf("\n\n\t\t\t1 : Use default input settings\n");
	printf("\n\t\t\t2 : Change default input settings\n");
	printf("\n\t\t\t3 : Quit\n");
	printf("\n\n\t\t\tEnter appropriate option : ");
	scanf("%d",&option);

	// before accepting new process input settings, free up memory
//	freeMemory();

	switch(option)
	{
		case 1 :
			no_of_processes = 10;
			no_io_opns = 2;
			arrival_range = no_of_processes-1;
			break;

		case 2 :
			clrscr();
			printf("\n\n\t\t      ====================================");
			printf("\n\t\t      CPU SCHEDULING ALGORITHMS SIMULATION");
			printf("\n\t\t      ====================================");

			printf("\n\n\t\t    Enter the number of processes (Max 100) : ");
			scanf("%d",&no_of_processes);
			printf("\n\n\t\t    Enter the number of I/O operations (<= 2) : ");
			scanf("%d",&no_io_opns);
			printf("\n\n\t\t    Enter the max arrival time of a process (>= %d) : ", no_of_processes-1);
			scanf("%d",&arrival_range);

			if (no_io_opns > 2)
				no_io_opns = 2;
			if (arrival_range < no_of_processes-1)
				arrival_range = no_of_processes-1;
			break;

		default : freeMemory(); exit(1);
	}
	generateProcesses();
}
/****************************************************************************/
