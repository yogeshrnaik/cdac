/****************************************************************/
/* ourhdr.h */
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>
#include "ourerror.h"

#define   MAXLINE 4096 
/****************************************************************/
/*byte order*/
#include<stdio.h>

int main(int argc, char **argv)
{
	union 
	{
		short  s;
    	char   c[sizeof(short)];
    } un;

	un.s = 0x0102;
	if (sizeof(short) == 2) 
	{
		if (un.c[0] == 1 && un.c[1] == 2)
			printf("big-endian\n");
		else if (un.c[0] == 2 && un.c[1] == 1)
			printf("little-endian\n");
		else
			printf("unknown\n");
	} 
	else
		printf("sizeof(short) = %d\n", sizeof(short));

	exit(0);
}
/****************************************************************/
/*
   Demonstration of compile time and run time limits

*/

#include<stdio.h>
#include<limits.h>
#include<errno.h>
#include<unistd.h>

int main() 
{
	
	long childmax=0,linkmax=0;
	
#ifdef FOPEN_MAX
	printf("Maximum number of streams that can be opened=%d\n",FOPEN_MAX);
#else
	printf("Duh...FOPEN_MAX not defined...using Dino-Unix?\n");
#endif

	errno=0;
	if( (childmax=sysconf(_SC_CHILD_MAX)) <0 ) 
	{
		if (errno==0) 
		{
			printf("Number of possible child processes is indeterminate\n");
		}
		else 
		{
			perror("sysconf(_SC_CHILD_MAX)");
			exit(1);
		}
	}
	else
	{
		printf("Number of possible child processess=%d\n",childmax);
	}

	

	errno=0;
	if( (linkmax=pathconf("somefile.txt",_PC_LINK_MAX)) <0 )
	{
		if (errno==0) 
		{
			printf("Number of possible links to somefile.txt is indeterminate\n");
		}
		else
		{
			perror("sysconf(_PC_LINK_MAX)");
			exit(1);
		}
	}
	else 
	{
		printf("Number of possible links to somefile.txt =%d\n",linkmax);
	}
}
/****************************************************************/
/*creation of hole in a file*/
#include	<stdio.h>
#include	<sys/types.h>
#include	<sys/stat.h>
#include	<fcntl.h>

int main(void)
{
	int fd;

	char	buf1[] = "abcdefghij";
	char	buf2[] = "ABCDEFGHIJ";

	if ( (fd = open("file.hole",O_CREAT|O_WRONLY|O_TRUNC, 
					S_IRUSR|S_IWUSR|S_IRGRP|S_IWGRP|S_IROTH)) < 0)
	{
		perror("creat error");
		exit(1);
	}

	if (write(fd, buf1, 10) != 10) 
	{
		perror("buf1 write error");
		exit(1);
	}
	/* offset now = 10 */

	if (lseek(fd, 40, SEEK_SET) == -1)
	{
		perror("lseek error");
		exit(1);
	}
	/* offset now = 40 */

	if (write(fd, buf2, 10) != 10) 
	{
		perror("buf2 write error");
		exit(1);
	}
	/* offset now = 50 */

	close(fd);

	exit(0);
}
/****************************************************************/
/*file flags*/
#include	<sys/types.h>
#include	<fcntl.h>
#include	"ourhdr.h"

int main(int argc, char *argv[])
{
	int		accmode, val;

	if (argc != 2)
		err_quit("usage: ./fileflags <descriptor#>");

	if ( (val = fcntl(atoi(argv[1]), F_GETFL, 0)) < 0)
		err_sys("fcntl error for fd %d", atoi(argv[1]));

	accmode = val & O_ACCMODE;
	if      (accmode == O_RDONLY)	printf("read only");
	else if (accmode == O_WRONLY)	printf("write only");
	else if (accmode == O_RDWR)		printf("read write");
	else err_dump("unknown access mode");

	if (val & O_APPEND)			printf(", append");
	if (val & O_NONBLOCK)		printf(", nonblocking");
#if	!defined(_POSIX_SOURCE) && defined(O_SYNC)
	if (val & O_SYNC)			printf(", synchronous writes");
#endif
	putchar('\n');
	exit(0);
}
/*run this as follows*/
#!/bin/bash
set -xv

gcc ourerror.c -c
gcc 3fileflags.c ourerror.o -o fileflags
./fileflags 0 < /dev/tty
./fileflags 1 > temp.foo

cat temp.foo
./fileflags 2 2>>temp.foo
./fileflags 5 5<>temp.foo
/****************************************************************/
#include	<sys/types.h>
#include	<sys/stat.h>
#include	"ourhdr.h"

int main(int argc, char *argv[])
{
	int			i;
	struct stat	buf;
	char		*ptr;

	for (i = 1; i < argc; i++)
	{
		printf("%s: ", argv[i]);
		if (lstat(argv[i], &buf) < 0)
		{
			err_ret("lstat error");
			continue;
		}

		if      (S_ISREG(buf.st_mode))	ptr = "regular";
		else if (S_ISDIR(buf.st_mode))	ptr = "directory";
		else if (S_ISCHR(buf.st_mode))	ptr = "character special";
		else if (S_ISBLK(buf.st_mode))	ptr = "block special";
		else if (S_ISFIFO(buf.st_mode))	ptr = "fifo";
#ifdef	S_ISLNK
		else if (S_ISLNK(buf.st_mode))	ptr = "symbolic link";
#endif
#ifdef	S_ISSOCK
		else if (S_ISSOCK(buf.st_mode))	ptr = "socket";
#endif
		else				ptr = "** unknown mode **";
		printf("%s\n", ptr);
	}
	exit(0);
}
/*run this as follows*/
#!/bin/bash
set -xv

gcc ourerror.c -c
gcc 4filetype.c ourerror.o -o filetype

#Create the below files, if required, using mkfifo, ln, etc
./filetype somefile.txt /home /dev/tty /dev/hda testfifo somelink 
/****************************************************************/
/*reading directory*/
#include	<sys/types.h>
#include	<dirent.h>
#include	"ourhdr.h"

int main(int argc, char *argv[])
{
	DIR				*dp;
	struct dirent	*dirp;

	if (argc != 2)
		err_quit("a single argument (the directory name) is required");

	if ( (dp = opendir(argv[1])) == NULL)
		err_sys("can't open %s", argv[1]);

	while ( (dirp = readdir(dp)) != NULL)
		printf("%s\n", dirp->d_name);

	closedir(dp);
	exit(0);
}
/****************************************************************/
#include	<errno.h>		/* for definition of errno */
#include	<stdarg.h>		/* ANSI C header file */
#include	<stdio.h>
#include 	"ourerror.h"


char	*pname = NULL;		/* caller can set this from argv[0] */

/* Nonfatal error related to a system call.
 * Print a message and return. */

void err_ret(const char *fmt, ...)
{
	va_list		ap;

	va_start(ap, fmt);
	err_doit(1, fmt, ap);
	va_end(ap);
	return;
}

/* Fatal error related to a system call.
 * Print a message and terminate. */

void err_sys(const char *fmt, ...)
{
	va_list		ap;

	va_start(ap, fmt);
	err_doit(1, fmt, ap);
	va_end(ap);
	exit(1);
}

/* Fatal error related to a system call.
 * Print a message, dump core, and terminate. */

void err_dump(const char *fmt, ...)
{
	va_list		ap;

	va_start(ap, fmt);
	err_doit(1, fmt, ap);
	va_end(ap);
	abort();		/* dump core and terminate */
	exit(1);		/* shouldn't get here */
}

/* Nonfatal error unrelated to a system call.
 * Print a message and return. */

void err_msg(const char *fmt, ...)
{
	va_list		ap;

	va_start(ap, fmt);
	err_doit(0, fmt, ap);
	va_end(ap);
	return;
}

/* Fatal error unrelated to a system call.
 * Print a message and terminate. */

void err_quit(const char *fmt, ...)
{
	va_list		ap;

	va_start(ap, fmt);
	err_doit(0, fmt, ap);
	va_end(ap);
	exit(1);
}

/* Print a message and return to caller.
 * Caller specifies "errnoflag". */

static void err_doit(int errnoflag, const char *fmt, va_list ap)
{
	int		errno_save;
	char	buf[MAXLINE];

	errno_save = errno;		/* value caller might want printed */
	vsprintf(buf, fmt, ap);
	if (errnoflag)
		sprintf(buf+strlen(buf), ": %s", strerror(errno_save));
	strcat(buf, "\n");
	fflush(stdout);		/* in case stdout and stderr are the same */
	fputs(buf, stderr);
	fflush(NULL);		/* flushes all stdio output streams */
	return;
}
/****************************************************************/
/*fork*/
#include	<sys/types.h>
#include	"ourhdr.h"

int		globvar = 6;		/* external variable in initialized data */
char	buf[] = "a write to stdout\n";

int main(void)
{
	int		var;		/* automatic variable on the stack */
	pid_t	pid;

	var = 88;
	if (write(STDOUT_FILENO, buf, sizeof(buf)-1) != sizeof(buf)-1)
		err_sys("write error");
	printf("before fork\n");	/* we don't flush stdout */

	if ( (pid = fork()) < 0)
		err_sys("fork error");
	else if (pid == 0) 
	{		/* child */
		globvar++;					/* modify variables */
		var++;
	}
	else
		sleep(2);				/* parent */

	printf("pid = %d, globvar = %d, var = %d\n", getpid(), globvar, var);
	exit(0);
}
/****************************************************************/
/*vfork*/
#include	<sys/types.h>
#include	"ourhdr.h"

int		globvar = 6;		/* external variable in initialized data */

int main(void)
{
	int		var;		/* automatic variable on the stack */
	pid_t	pid;

	var = 88;
	printf("before vfork\n");	/* we don't flush stdio */

	if ( (pid = vfork()) < 0)
		err_sys("vfork error");
	else if (pid == 0) 
	{		/* child */
		globvar++;					/* modify parent's variables */
		var++;
		_exit(0);				/* child terminates */
	}

	/* parent */
	printf("pid = %d, globvar = %d, var = %d\n", getpid(), globvar, var);
	exit(0);
}
/****************************************************************/
/********************************************************
			DISCLAIMER
This code is purely for instructional purpose. 
********************************************************/
/****
TCP Echo Server.

  This program provides simple echo service but...
* Does not handle zombies
****/
#include <sys/types.h>  /* for AF_INET, etc                   */
#include <sys/socket.h> /* for socket, connect,bind,          */
                        /* listen, the works                  */
#include <arpa/inet.h>  /* for inet_aton, inet_pton, etc      */
#include <netinet/in.h> /* for the struct sockaddr_in,        */
                        /* and also htons, etc                */
#include <errno.h>      /* for errno                          */
#include <stdio.h>      /* has perror, in addition to         */
                        /* other essentials                   */
#include <stdlib.h>     /* for atoi                           */
#include <string.h>     /* for strerror                       */
#include <strings.h>    /* for bzero, etc                     */

#define PORT  8888
#define MAXLINE 4096

/* This function reads a line(i.e. till '\n' is found) */
/* having length less than maxlen */
ssize_t readline(int fd, void *vptr, size_t maxlen) 
{													
	int		n, rc;
	char	c, *ptr;

	ptr = vptr;
	for (n = 1; n < maxlen; n++) 
	{
		again : 
		if ( (rc = read(fd, &c, 1)) == 1) /** REMEMBER read() IS A SLOW SYSTEM CALL **/
		{
			*ptr++ = c;
			if (c == '\n')
				break;		/* newline is stored, like fgets() */
		} 
		else if (rc == 0) 
		{
			if (n == 1)
				return(0);	/* EOF, no data read */
			else
				break;		/* EOF, some data was read */
		} 
		else
		{
			if (errno == EINTR) /** HANDLING EINTR FOR A SLOW SYSTEM CALL **/
			{
				goto again;
			}
			return(-1);		/* error, errno set by read() */
		}
			
	}

	*ptr = 0;	/* null terminate like fgets() */
	return(n);
}
/*----------------------------------------*/			
ssize_t writen(int fd, const void *vptr, size_t n)	/* Write "n" bytes to a descriptor. */
{
	size_t		nleft;
	ssize_t		nwritten;
	const char	*ptr;

	ptr = vptr;
	nleft = n;

	/* write() may not write all the 'n' bytes in one go...so    */
	/* we need to call write() till all the bytes have been written */

	while (nleft > 0) 
	{

		if ( (nwritten = write(fd, ptr, nleft)) <= 0) 
		{
			if (nwritten<0 && errno == EINTR)
				nwritten = 0;		/* and call write() again */
			else
				return(-1);			/* error */
		}

		nleft -= nwritten;
		ptr   += nwritten;
	}
	return(n);
}
/* end writen */


/* some server processing function */

/*

Please excuse me if the figure below does not appear as required...
Change window size if required


        |-------------------|                   |-------------------|
   fgets|                   |writen     readline|                   |
------->|                   |------------------>|                   |
        |       client      |                   |       server      |
<-------|                   |<------------------|                   |
   fputs|                   |readline     writen|                   |
        |-------------------|                   |-------------------|


*/
/*----------------------------------------*/
void srv_echo(int sockfd)
{
	ssize_t		n;
	char		line[MAXLINE];

	for ( ; ; ) 
	{
		if ( (n = readline(sockfd, line, MAXLINE)) < 0)
		{
			perror("Error in readline()");
			exit(-1); 		
		} 
		else if (n == 0)
			return;		/* connection closed by other end */

		if (writen(sockfd, line, n)!= n)
		{
			perror("Error in writen()");
			exit(-1); 		
		}
	}
}
/*----------------------------------------*/
int main(int argc, char* argv[])
{
	struct sockaddr_in		servaddr;	/*Server's local address*/
	int						lsock;		/* socket to listen on */
	int						csock;		/* socket for communication with client */
	
	/*Address initialization*/
	bzero(&servaddr,sizeof(servaddr));

	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=htonl(INADDR_ANY);
	if(argc>1)
		servaddr.sin_port=htons(atoi(argv[1]));
	else
		servaddr.sin_port=htons(PORT);

	if( (lsock=socket(AF_INET,SOCK_STREAM,0)) < 0 )
	{
		fprintf(stderr, "Error in socket():%s\n",strerror(errno)); 
		exit(-1);
	}


	if( (bind(lsock,(struct sockaddr*)&servaddr,sizeof(servaddr))) <0 )
	{
		perror("Error in bind()");
		exit(-1);
	}
	
	if( (listen(lsock,SOMAXCONN)) <0 )
	{
		perror("Error in listen()"); 
		exit(-1);
	}
			
	printf("Echo server: listening on port %d\n",ntohs(servaddr.sin_port));
		
	while(1)
	{
		if( (csock=accept(lsock,NULL,NULL)) < 0)
		{
			perror("Error in accept()");
			exit(-1);
		}
					                                  
		
		printf("Contacted by a client....will now spawn a child...\n");
		

		/*Delegating to my clone...*/
		if( fork()==0)
		{
			/*Child*/
			close(lsock); /* Client will no longer listen*/
			srv_echo(csock); /*Transact*/
			fprintf(stdout, "The client has closed the connection\n");
			close(csock); /*Ciao*/
			exit(0);
		}	
		else
		{	
			/*Parent*/
			close(csock); /*Parent will not interact with the client*/
		}
	}
	
}
/****************************************************************/
/********************************************************
						DISCLAIMER
This code is purely for instructional purpose.
********************************************************/
/****
TCP Echo Server.
This program provides simple echo service and it...
* Does handle zombies
****/
#include <sys/types.h>  /* for AF_INET, etc                   */
#include <sys/socket.h> /* for socket, connect,bind,          */
                        /* listen, the works                  */
#include <arpa/inet.h>  /* for inet_aton, inet_pton, etc      */
#include <netinet/in.h> /* for the struct sockaddr_in,        */
                        /* and also htons, etc                */
#include <errno.h>      /* for errno                          */
#include <signal.h>     /* wanna play with signals, anybody?  */
#include <stdio.h>      /* has perror, in addition to         */
                        /* other essentials                   */
#include <stdlib.h>     /* for atoi                           */
#include <string.h>     /* for strerror                       */
#include <strings.h>    /* for bzero, etc                     */
#include <sys/wait.h>       /* for waitpid                        */

#define PORT  8888
#define MAXLINE 4096

/* This function reads a line(i.e. till '\n' is found) */
/* having length less than maxlen */
ssize_t readline(int fd, void *vptr, size_t maxlen) 
{													
	int		n, rc;
	char	c, *ptr;

	ptr = vptr;
	for (n = 1; n < maxlen; n++) 
	{
		again : 
		if ( (rc = read(fd, &c, 1)) == 1) /** REMEMBER read() IS A SLOW SYSTEM CALL **/
		{
			*ptr++ = c;
			if (c == '\n')
				break;		/* newline is stored, like fgets() */
		} 
		else if (rc == 0) 
		{
			if (n == 1)
				return(0);	/* EOF, no data read */
			else
				break;		/* EOF, some data was read */
		} 
		else
		{
			if (errno == EINTR) /** HANDLING EINTR FOR A SLOW SYSTEM CALL **/
			{
				goto again;
			}
			return(-1);		/* error, errno set by read() */
		}
			
	}

	*ptr = 0;	/* null terminate like fgets() */
	return(n);
}
/*----------------------------------------*/
ssize_t writen(int fd, const void *vptr, size_t n)	/* Write "n" bytes to a descriptor. */
{
	size_t		nleft;
	ssize_t		nwritten;
	const char	*ptr;

	ptr = vptr;
	nleft = n;

	/* write() may not write all the 'n' bytes in one go...so    */
	/* we need to call write() till all the bytes have been written */

	while (nleft > 0) 
	{

		if ( (nwritten = write(fd, ptr, nleft)) <= 0) 
		{
			if (nwritten<0 && errno == EINTR)
				nwritten = 0;		/* and call write() again */
			else
				return(-1);			/* error */
		}

		nleft -= nwritten;
		ptr   += nwritten;
	}
	return(n);
}
/* end writen */
/*----------------------------------------*/
/* some server processing function */
/*
Please excuse me if the figure below does not appear as required...
Change window size if required


        |-------------------|                   |-------------------|
   fgets|                   |writen     readline|                   |
------->|                   |------------------>|                   |
        |       client      |                   |       server      |
<-------|                   |<------------------|                   |
   fputs|                   |readline     writen|                   |
        |-------------------|                   |-------------------|
*/
void srv_echo(int sockfd)
{
	ssize_t		n;
	char		line[MAXLINE];

	for ( ; ; ) 
	{
		if ( (n = readline(sockfd, line, MAXLINE)) < 0)
		{
			perror("Error in readline()");
			exit(-1); 		
		} 
		else if (n == 0)
			return;		/* connection closed by other end */

		if (writen(sockfd, line, n)!= n)
		{
			perror("Error in writen()");
			exit(-1); 		
		}
	}
}
/*----------------------------------------*/
void sig_handler(int signo)
{
	pid_t pid;
	int stat;
	
	while( (pid=waitpid(-1,&stat,WNOHANG)) > 0 )
		printf("Child %d terminated\n",pid);
	
	return;
}
/*----------------------------------------*/
int main(int argc, char* argv[])
{
	struct sockaddr_in servaddr; /*Server's local address*/
	int lsock;                   /* socket to listen on */
	int csock;             /* socket for communication with client */
	
	//Signal handling stuff
	struct sigaction act,oact;

	act.sa_handler=sig_handler;
	sigemptyset(&act.sa_mask);
	act.sa_flags=0;

#ifdef SA_RESTART
	act.sa_flags|= SA_RESTART;
#endif
	
	if( (sigaction(SIGCHLD, &act, &oact)) < 0)
	{
		fprintf(stderr, "Error in sigaction():%s\n",strerror(errno));
		exit(-1);
	}

	/*Address initialization*/
	bzero(&servaddr,sizeof(servaddr));

	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=htonl(INADDR_ANY);

	if(argc>1)
		servaddr.sin_port=htons(atoi(argv[1]));
	else
		servaddr.sin_port=htons(PORT);

	if( (lsock=socket(AF_INET,SOCK_STREAM,0)) < 0 )
	{
		fprintf(stderr, "Error in socket():%s\n",strerror(errno)); 
		exit(-1);
	}

	if( (bind(lsock,(struct sockaddr*)&servaddr,sizeof(servaddr))) <0 )
	{
		perror("Error in bind()");
		exit(-1);
	}

	if( (listen(lsock,SOMAXCONN)) <0 )
	{
		perror("Error in listen()"); 
		exit(-1);
	}
			
	printf("Echo server: listening on port %d\n",ntohs(servaddr.sin_port));
		
	while(1)
	{
		if( (csock=accept(lsock,NULL,NULL)) < 0) /*BEWARE accept IS A SLOW SYSTEM CALL*/
		{

			if(errno=EINTR)/*EINTR handled bcoz slow system 
					 calls may throw this error if interrupted*/
				continue;
			else
			{
				perror("Error in accept()");
				exit(-1);
			}
		}
					                                  
		
		printf("Contacted by a client....will now spawn a child...\n");
		
		/*Delegating to my clone...*/
		if( fork()==0)
		{
			/*Child*/
			close(lsock); 
			srv_echo(csock); /* Transact */
			fprintf(stdout, "The client has closed the connection\n");
			close(csock);  /*Ciao*/
			exit(0);
		}	
		else
		{	
			/*parent*/
			close(csock); 
		}
	}
	
}
/****************************************************************/
/********************************************************
						DISCLAIMER
This code is purely for instructional purpose. Please use
it on your own risk. 
********************************************************/
/****
TCP Echo Client.
This program acts as a simple echo client but...
* It does not handle server crashes.
* Does avoid SIGPIPE, but no explicit handler for it.
****/

#include <sys/types.h>  /* for AF_INET, etc                   */
#include <sys/socket.h> /* for socket, connect,bind,          */
                        /* listen, the works                  */
#include <arpa/inet.h>  /* for inet_aton, inet_pton, etc      */
#include <netinet/in.h> /* for the struct sockaddr_in,        */
                        /* and also htons, etc                */
#include <errno.h>      /* for errno                          */
#include <stdio.h>      /* has perror, in addition to         */
                        /* other essentials                   */
#include <stdlib.h>     /* for atoi                           */
#include <string.h>     /* for strerror                       */
#include <strings.h>    /* for bzero, etc                     */

#define PORT  8888
#define MAXLINE 4096

/* This function reads a line(i.e. till '\n' is found) */
/* having length less than maxlen */
ssize_t readline(int fd, void *vptr, size_t maxlen) 
{													
	int		n, rc;
	char	c, *ptr;

	ptr = vptr;
	for (n = 1; n < maxlen; n++) 
	{
		again : 
		if ( (rc = read(fd, &c, 1)) == 1) /** REMEMBER read() IS A SLOW SYSTEM CALL **/
		{
			*ptr++ = c;
			if (c == '\n')
				break;		/* newline is stored, like fgets() */
		} 
		else if (rc == 0) 
		{
			if (n == 1)
				return(0);	/* EOF, no data read */
			else
				break;		/* EOF, some data was read */
		} 
		else
		{
			if (errno == EINTR) /** HANDLING EINTR FOR A SLOW SYSTEM CALL **/
			{
				goto again;
			}
			return(-1);		/* error, errno set by read() */
		}
			
	}

	*ptr = 0;	/* null terminate like fgets() */
	return(n);
}
/*----------------------------------------*/				
ssize_t writen(int fd, const void *vptr, size_t n)	/* Write "n" bytes to a descriptor. */
{
	size_t		nleft;
	ssize_t		nwritten;
	const char	*ptr;

	ptr = vptr;
	nleft = n;

	/* write() may not write all the 'n' bytes in one go...so    */
	/* we need to call write() till all the bytes have been written */

	while (nleft > 0) 
	{

		if ( (nwritten = write(fd, ptr, nleft)) <= 0) 
		{
			if (nwritten<0 && errno == EINTR)
				nwritten = 0;		/* and call write() again */
			else
				return(-1);			/* error */
		}

		nleft -= nwritten;
		ptr   += nwritten;
	}
	return(n);
}
/* end writen */
/*----------------------------------------*/
/* some client processing function */
/*
Please excuse me if the figure below does not appear as required...
Please resize window if required


        |-------------------|                   |-------------------|
   fgets|                   |writen     readline|                   |
------->|                   |------------------>|                   |
        |       client      |                   |       server      |
<-------|                   |<------------------|                   |
   fputs|                   |readline     writen|                   |
        |-------------------|                   |-------------------|


*/
/*----------------------------------------*/
void cli_echo(FILE *fp, int sockfd)
{
	char	sendline[MAXLINE], recvline[MAXLINE];
	char	*rptr;
	int		n;

	if ( (rptr = fgets(sendline, MAXLINE, fp)) == NULL && ferror(fp) )
	{
		perror("Error in fputs()");
		exit(-1);
	}

	while ( rptr != NULL ) 
	{
		n = strlen(sendline);

		if (writen(sockfd, sendline, n) != n)
		{
			perror("Error in writen()");
			exit(-1);
		}

		if (readline(sockfd, recvline, MAXLINE) == 0)
		{
			perror("Error in readline()");
			exit(-1);
		}


		fputs(recvline, stdout);

		if ( (rptr = fgets(sendline, MAXLINE, fp)) == NULL && ferror(fp) )
		{
			perror("Error in fputs()");
			exit(-1);
		}
	}
}
/*----------------------------------------*/
int  main(int argc, char* argv[])
{
	int sockfd;
	struct sockaddr_in servaddr,cliaddr;


	if(argc<2)
	{
		fprintf(stderr,"Usage: echoclient IPaddr [port]\n");
		exit(-1);
	}

	/* Create Socket */

	if( (sockfd=socket(AF_INET,SOCK_STREAM,0)) < 0)
	{
		perror("Problem with socket()");
		exit(-1);
	}

	
	/* Server address initialization */
	
	bzero(&servaddr,sizeof(servaddr));
	
	servaddr.sin_family=AF_INET;

	if( inet_pton(AF_INET,argv[1],&(servaddr.sin_addr))<=0)
	{
		fprintf(stderr,"Improper address or family\n");
		exit(-1);
	}
		
	if(argc>2)
		servaddr.sin_port=htons(atoi(argv[2]));
	else
		servaddr.sin_port=htons(PORT);


	if( (connect(sockfd,(struct sockaddr*)&servaddr,sizeof(servaddr))) < 0)
	{
		perror("Error in connect()");
		exit(-1);
	}
	
	printf("Established connection with echo server\n");
	

	cli_echo(stdin, sockfd);
	
	printf("Will now close connection with server\n");
	
	close(sockfd);

}
/****************************************************************/
/*Program to illustrate daemonization
 Trivial error handling not done at some places, for readability */
#include	<stdio.h>
#include	<sys/types.h>
#include	<signal.h>
#include	<syslog.h>
#include	<errno.h>

#define	MAXFD	64
char pname[]="My daemon";
/*----------------------------------------*/
void daemon_init(const char *pname, int facility)
{
	int		i;
	pid_t	pid;

	if ( (pid = fork()) != 0)
		exit(0);			/* parent terminates */

	/* 1st child continues */
	setsid();				/* become session leader */

	struct sigaction act, oact;
	
	act.sa_handler=SIG_IGN;
	sigemptyset(&act.sa_mask);
	act.sa_flags=0;

#ifdef SA_RESTART
	act.sa_flags|= SA_RESTART;
#endif
	
	if( (sigaction(SIGHUP, &act, &oact)) < 0)
	{
		fprintf(stderr, "Error in sigaction():%s\n",strerror(errno));
		exit(-1);
	}
	
	if ( (pid = fork()) != 0)
		exit(0);			/* 1st child terminates */

	/* 2nd child continues */

	chdir("/");				/* change working directory */

	umask(0);				/* clear our file mode creation mask */

	for (i = 0; i < MAXFD; i++)
		close(i);

	openlog(pname, LOG_PID, facility);
}
/*----------------------------------------*/
int main()
{
	daemon_init(pname,LOG_USER);
	pause();
}
/****************************************************************/
/* 1pipe.c */
#include	"ourhdr.h"

int main(void)
{
	int		n, fd[2];
	pid_t	pid;
	char	line[MAXLINE];

	if (pipe(fd) < 0)
		err_sys("pipe error");

	if ( (pid = fork()) < 0)
		err_sys("fork error");

	else if (pid > 0) {		/* parent */
		close(fd[0]);
		write(fd[1], "hello world\n", 12);

	} else {				/* child */
		close(fd[1]);
		n = read(fd[0], line, MAXLINE);
		write(STDOUT_FILENO, line, n);
	}

	exit(0);
}
/****************************************************************/
/* 2popen.c */
#include	<sys/wait.h>
#include	"ourhdr.h"

#define	PAGER	"${PAGER:-more}" /* environment variable, or default */

int main(int argc, char *argv[])
{
	char	line[MAXLINE];
	FILE	*fpin, *fpout;

	if (argc != 2)
		err_quit("usage: program_name <pathname>");
	if ( (fpin = fopen(argv[1], "r")) == NULL)
		err_sys("can't open %s", argv[1]);

	if ( (fpout = popen(PAGER, "w")) == NULL)
		err_sys("popen error");

		/* copy argv[1] to pager */
	while (fgets(line, MAXLINE, fpin) != NULL) {
		if (fputs(line, fpout) == EOF)
			err_sys("fputs error to pipe");
	}
	if (ferror(fpin))
		err_sys("fgets error");
	if (pclose(fpout) == -1)
		err_sys("pclose error");
	exit(0);
}
/****************************************************************/
/* 3fifo-recvr.c */
#include	<sys/types.h>
#include	<sys/stat.h>
#include	<fcntl.h>
#include	"ourhdr.h"

#define	FIFO "/tmp/temp-someuniqueID.fifo"
#define    FILE_MODE       (S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH)

static int	read_cnt;
static char	*read_ptr;
static char	read_buf[MAXLINE];

ssize_t Readline(int fd, void *vptr, size_t maxlen);

int main(void)
{
	int		fdread;
	int 		n=1;
	char 		buff[MAXLINE];

	/*Note: We can use better techniques to ensure that our
	  FIFO name is unique to us*/
	unlink(FIFO);
	if (mkfifo(FIFO, FILE_MODE) < 0)
		err_sys("mkfifo error");

	printf("Will wait till a writer opens the FIFO\n");
	
	if ( (fdread = open(FIFO, O_RDONLY)) < 0)
		err_sys("open error for reading");

	for(;;) 
	{
		n=Readline(fdread,buff,MAXLINE);
		if(n>0) 
		{
			printf("%s",buff);
		}
		else
			break;
		
	}
	exit(0);
}
/*----------------------------------------*/
static ssize_t my_read(int fd, char *ptr)
{

	if (read_cnt <= 0) 
	{
	again:
		if ( (read_cnt = read(fd, read_buf, sizeof(read_buf))) < 0)
		{
			if (errno == EINTR)
				goto again;
			return(-1);
		} 
		else if (read_cnt == 0)
			return(0);
		read_ptr = read_buf;
	}
	read_cnt--;
	*ptr = *read_ptr++;
	return(1);
}
/*----------------------------------------*/
ssize_t readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t	n, rc;
	char	c, *ptr;

	ptr = vptr;
	for (n = 1; n < maxlen; n++) {
		if ( (rc = my_read(fd, &c)) == 1) 
		{
			*ptr++ = c;
			if (c == '\n')
				break;	/* newline is stored, like fgets() */
		} 
		else if (rc == 0) {
			*ptr = 0;
			return(n - 1);	/* EOF, n - 1 bytes were read */
		}
		else
			return(-1);		/* error, errno set by read() */
	}

	*ptr = 0;	/* null terminate like fgets() */
	return(n);
}
/*----------------------------------------*/
ssize_t Readline(int fd, void *ptr, size_t maxlen)
{
	ssize_t		n;

	if ( (n = readline(fd, ptr, maxlen)) < 0)
		err_sys("readline error");
	return(n);
}
/****************************************************************/
/* 3fifo-sendr.c */
#include	<sys/types.h>
#include	<sys/stat.h>
#include	<fcntl.h>
#include	"ourhdr.h"

#define	FIFO "/tmp/temp-someuniqueID.fifo"

void clr_fl(int fd, int flags);

ssize_t Writen(int sockd, const  void *vptr, size_t n);
int main(void)
{
	int		fdwrite;
	int 	n=1;
	char 	buff[MAXLINE];
	
	char	*rptr;

	/*Note: We can use better techniques to ensure that our
	  FIFO name is unique to us*/
	if ( (fdwrite = open(FIFO, O_WRONLY | O_NONBLOCK)) < 0) 
	{
		if(errno==ENXIO) 
		{
			fprintf(stderr,"The listener has not yet opened the FIFO. Try after starting Listener\n");
			exit(-1);
		}
		else
			err_sys("open error for writing");
	}
	
	clr_fl(fdwrite, O_NONBLOCK);
	

	if ( (rptr = fgets(buff, MAXLINE,stdin )) == NULL && ferror(stdin) )
		err_sys("Error in fputs()");

	while ( rptr != NULL ) 
	{
		n = strlen(buff);

		Writen(fdwrite,buff,n);

		if ( (rptr = fgets(buff, MAXLINE, stdin)) == NULL && ferror(stdin) )
			err_sys("Error in fputs()");
	}

}
/*----------------------------------------*/
void clr_fl(int fd, int flags)
				/* flags are the file status flags to turn off */
{
	int		val;

	if ( (val = fcntl(fd, F_GETFL, 0)) < 0)
		err_sys("fcntl F_GETFL error");

	val &= ~flags;		/* turn flags off */

	if (fcntl(fd, F_SETFL, val) < 0)
		err_sys("fcntl F_SETFL error");
}
/*----------------------------------------*/
ssize_t writen(int sockd, const  void *vptr, size_t n)
{
	size_t nleft;
	ssize_t nwritten;
	const char *buffer;

	buffer = vptr;
	nleft = n;

	while( nleft > 0 )
	{
		if( ( nwritten = write(sockd, buffer, nleft)) <= 0)
		{
			if(errno == EINTR)
				nwritten = 0;
			else
				return -1;
		}
		nleft -= nwritten;
		buffer += nwritten;
	}
	return n;
}
/*----------------------------------------*/
ssize_t Writen(int sockd, const  void *vptr, size_t n) {
	int ret;

	if((ret=writen(sockd,vptr,n)) <0 )
	{
		err_sys("Error in writen");
	}

	return ret;
}	
/****************************************************************/
/* 4semdemo.c */
#include "ourhdr.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <ctype.h>
/*----------------------------------------*/
int issdigit(char* str, int len) 
{
	
	int i=0;
	
	if(str==NULL)
		return -1;

	while(str[i]&&i<len) 
	{
		if(!isdigit(str[i]))
			return 0;
		i++;
	}

	if(i==len)
		return 1;
	else
		return -1;
}
/*----------------------------------------*/
int main(int argc, char* argv[]) 
{

	
	
	key_t   semkey;          /* key to pass to semget() */
	int     semflg;       /* semflg to pass to semget() */
	int     nsems;        /* nsems to pass to semget() */
	int     semid;        /* return value from semget() */
	struct  sembuf semoparry[1]; /*For semop*/
	ushort sarray[1];     /*for reading value of semval*/

	union semun 
	{
		int val;
		struct semid_ds *buf;
		ushort *array;
	} semarg; /* for semctl*/
	semarg.val=1;
	
	/* Arguments: Path and project ID for 
	   Semaphore access*/

	if(argc<3||issdigit(argv[2],strlen(argv[2]))<1) 
		err_quit("Usage: progname path int_projectID");
	

	/*Generate key*/
	
	if((semkey=ftok(argv[1],atoi(argv[2])))<0)
		err_sys("Problem during semaphore key creation");

	/*Initialize other parameters*/
	
#ifndef SEM_A
#define SEM_A   0200    /* alter permission */
#endif

#ifndef SEM_R
#define SEM_R   0400    /* read permission */
#endif
	semflg=SEM_R|SEM_A|(SEM_R>>3)|IPC_CREAT; /*user_read+user_alter+group_read*/
	
	nsems=1;
	
	
	/*Create semaphore*/
	if((semid=semget(semkey,nsems,semflg))<0) 
		err_sys("Error when creating semaphore");
	
	
	printf("semid=%d\n",semid);
	
	/*Initialize the semaphore value*/
	/*Here, we are simulating a mutex*/

	
	if(semctl(semid,0,SETVAL,semarg)<0)
		err_sys("Error when initializing semaphore value");

	/*Read value of semval*/
	semarg.array=sarray;
	
	if(semctl(semid,0,GETALL,semarg)<0)
		err_sys("Error when getting semaphore value");

	printf("Semval=%d\n",sarray[0]);
	
	char identity[8]; //parent or child
	
	if(fork()!=0) 
	{

		//Parent
		strcpy(identity,"Parent");
	}
	else 
	{
		//Child
		strcpy(identity,"Child");
		
		//Force to execute after parent
		
		sleep(2);
	}
	/*Get ready to enter the critical region*/
	
	semoparry[0].sem_num=0;
	semoparry[0].sem_op=-1;
	semoparry[0].sem_flg=SEM_UNDO;

#define MY_REMSEM //define if you want to see what happens if you remove use of semaphores
	
#ifndef MY_REMSEM 
	while(1) 
	{
		if(semop(semid,semoparry,1)<0) 
		{
			if(errno==EINTR)
				continue;
			else
				err_sys("Error in semop");
		}
		else
			break;
	}
#endif
	/*-------CRITICAL REGION--------------*/
		/* We are in!
		   Here, merely do a sleep to simulate
		   the important work we are doing
		   :-)
		 */

	printf("%s:",identity);
	printf("Entered into critical region\n");
	
	semarg.array=sarray;
	
	if(semctl(semid,0,GETALL,semarg)<0)
		err_sys("Error when getting semaphore value");

	printf("Semval=%d\n",sarray[0]);
	
	sleep(7);

	/*------END OF CRITICAL REGION--------*/
	
	semoparry[0].sem_num=0;
	semoparry[0].sem_op=1; /*To release resource*/
	semoparry[0].sem_flg=SEM_UNDO;
	
#ifndef MY_REMSEM
	if(semop(semid,semoparry,1)<0) 
		err_sys("Error in semop"); /*Ouch...but SEM_UNDO will take care of things*/
#endif
	
	printf("Have come out of critical region and released resources\n");
}
/****************************************************************/
/********************************************************
						DISCLAIMER
This code is purely for instructional purpose.
********************************************************/
/****
TCP Echo Server.
This program provides simple echo service and it...
* Uses threads
****/
#include <sys/types.h>  /* for AF_INET, etc                   */
#include <sys/socket.h> /* for socket, connect,bind,          */
                        /* listen, the works                  */
#include <arpa/inet.h>  /* for inet_aton, inet_pton, etc      */
#include <netinet/in.h> /* for the struct sockaddr_in,        */
                        /* and also htons, etc                */
#include <errno.h>      /* for errno                          */
#include <signal.h>     /* wanna play with signals, anybody?  */
#include <stdio.h>      /* has perror, in addition to         */
                        /* other essentials                   */
#include <stdlib.h>     /* for atoi                           */
#include <string.h>     /* for strerror                       */
#include <strings.h>    /* for bzero, etc                     */
#include <sys/wait.h>       /* for waitpid                        */
#include <pthread.h>

#define PORT  8888
#define MAXLINE 4096

static void* doit(void*); /*Each thread executes this function*/

/* This function reads a line(i.e. till '\n' is found) */
/* having length less than maxlen */
ssize_t readline(int fd, void *vptr, size_t maxlen) 
{													
	int		n, rc;
	char	c, *ptr;

	ptr = vptr;
	for (n = 1; n < maxlen; n++) 
	{
		again : 
		if ( (rc = read(fd, &c, 1)) == 1) /** REMEMBER read() IS A SLOW SYSTEM CALL **/
		{
			*ptr++ = c;
			if (c == '\n')
				break;		/* newline is stored, like fgets() */
		} 
		else if (rc == 0) 
		{
			if (n == 1)
				return(0);	/* EOF, no data read */
			else
				break;		/* EOF, some data was read */
		} 
		else
		{
			if (errno == EINTR) /** HANDLING EINTR FOR A SLOW SYSTEM CALL **/
			{
				goto again;
			}
			return(-1);		/* error, errno set by read() */
		}
			
	}

	*ptr = 0;	/* null terminate like fgets() */
	return(n);
}
/*----------------------------------------*/						
ssize_t writen(int fd, const void *vptr, size_t n)	/* Write "n" bytes to a descriptor. */
{
	size_t		nleft;
	ssize_t		nwritten;
	const char	*ptr;

	ptr = vptr;
	nleft = n;

	/* write() may not write all the 'n' bytes in one go...so    */
	/* we need to call write() till all the bytes have been written */

	while (nleft > 0) 
	{

		if ( (nwritten = write(fd, ptr, nleft)) <= 0) 
		{
			if (nwritten<0 && errno == EINTR)
				nwritten = 0;		/* and call write() again */
			else
				return(-1);			/* error */
		}

		nleft -= nwritten;
		ptr   += nwritten;
	}
	return(n);
}
/* end writen */
/*----------------------------------------*/
/* some server processing function */
/*

Please excuse me if the figure below does not appear as required...
Change window size if required


        |-------------------|                   |-------------------|
   fgets|                   |writen     readline|                   |
------->|                   |------------------>|                   |
        |       client      |                   |       server      |
<-------|                   |<------------------|                   |
   fputs|                   |readline     writen|                   |
        |-------------------|                   |-------------------|
*/
/*----------------------------------------*/
void srv_echo(int sockfd)
{
	ssize_t		n;
	char		line[MAXLINE];

	for ( ; ; ) 
	{
		if ( (n = readline(sockfd, line, MAXLINE)) < 0)
		{
			perror("Error in readline()");
			exit(-1); 		
		} 
		else if (n == 0)
			return;		/* connection closed by other end */

		if (writen(sockfd, line, n)!= n)
		{
			perror("Error in writen()");
			exit(-1); 		
		}
	}
}
/*----------------------------------------*/
int main(int argc, char* argv[])
{
	struct sockaddr_in servaddr; /*Server's local address*/
	int lsock;                   /* socket to listen on */
	int csock;             /* socket for communication with client */
	pthread_t tid;		/*Thread ID*/

	/*Address initialization*/
	bzero(&servaddr,sizeof(servaddr));

	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=htonl(INADDR_ANY);

	if(argc>1)
		servaddr.sin_port=htons(atoi(argv[1]));
	else
		servaddr.sin_port=htons(PORT);

	if( (lsock=socket(AF_INET,SOCK_STREAM,0)) < 0 )
	{
		fprintf(stderr, "Error in socket():%s\n",strerror(errno)); 
		exit(-1);
	}

	if( (bind(lsock,(struct sockaddr*)&servaddr,sizeof(servaddr))) <0 )
	{
		perror("Error in bind()");
		exit(-1);
	}

	if( (listen(lsock,SOMAXCONN)) <0 )
	{
		perror("Error in listen()"); 
		exit(-1);
	}
			
	printf("Echo server: listening on port %d\n",ntohs(servaddr.sin_port));
		
	while(1)
	{
		if( (csock=accept(lsock,NULL,NULL)) < 0) /*BEWARE accept IS A SLOW SYSTEM CALL*/
		{

			if(errno=EINTR)/*EINTR handled bcoz slow system 
					 calls may throw this error if interrupted*/
				continue;
			else
			{
				perror("Error in accept()");
				exit(-1);
			}
		}
					                                  
		
		printf("Contacted by a client....will now spawn a thread...\n");
		
		if(pthread_create(&tid,NULL,&doit,(void*)csock)!=0) {
			perror("Error in accept()");
			if(errno!=EAGAIN)
				exit(-1);
		}

	}
	
}
/*----------------------------------------*/
static void * doit(void* arg) 
{
	if(pthread_detach(pthread_self())!=0) 
	{
		return(NULL);
	}
	srv_echo((int) arg);
	close((int) arg);
	return(NULL);
}
/****************************************************************/
/****
//TCP Echo server for instructional purposes
//This is an echo server that uses I/O multiplexing. 
//You connect to it, 
//send it a newline terminated string
//and it sends back the string.
***************************************************************/
#include <stdio.h>      /* has perror, in addition to         */
                        /* other essentials                   */
#include <netinet/in.h> /* for the struct sockaddr_in,        */
                        /* and also htons, etc                */
#include <arpa/inet.h>  /* for inet_aton, inet_pton, etc      */ 
#include <sys/socket.h> /* for socket, connect,bind,          */
                        /* listen, the works                  */
#include <sys/types.h>  /* for AF_INET, etc                   */
#include <unistd.h>     /* for close, fork, exec, etc         */
#include <strings.h>    /* for bzero, etc                     */
#include <stdlib.h>     /* for atoi                           */
#include <errno.h>      /* for errno                          */
#include <sys/select.h> /* for select                         */


#define PORT 7777
#define MAXLINE 60

ssize_t Readline(int fd, void *vptr, size_t maxlen);
ssize_t Writen(int fd, const void *vptr, size_t n);
/*----------------------------------------*/
int main(int argc, char *argv[])
{
	int			i, maxi, maxfd, listenfd, connfd, sockfd;
	int			nready, client[FD_SETSIZE];
	ssize_t			n;
	fd_set			rset, allset;
	char			line[MAXLINE];
	socklen_t		clilen;
	struct sockaddr_in      cliaddr, servaddr;
	char                    clientip[INET_ADDRSTRLEN];
	char*			clientiparray[FD_SETSIZE];
	
	if( (listenfd = socket(AF_INET, SOCK_STREAM, 0))<0)
	{
		perror("Error in socket()");
		exit(-1);
	}

	bzero(&servaddr,sizeof(servaddr));

	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=htonl(INADDR_ANY);

	if(argc>1)
		servaddr.sin_port=htons(atoi(argv[1]));
	else
		servaddr.sin_port=htons(PORT);

	if( (listenfd=socket(AF_INET,SOCK_STREAM,0)) < 0 )
	{
		perror("Error in socket()"); 
		exit(-1);
	}


	if( (bind(listenfd,(struct sockaddr*)&servaddr,sizeof(servaddr))) <0 )
	{
		perror("Error in bind()");
		exit(-1);
	}
	
	if( (listen(listenfd,SOMAXCONN)) <0 )
	{
		perror("Error in listen()"); 
		exit(-1);
	}
			
	printf("Echo server: listening on port %d\n",ntohs(servaddr.sin_port));

	/* initialize */
	maxi = -1;	/* max index into client[] array */
	for (i = 0; i < FD_SETSIZE; i++)
		client[i] = -1;	 /* -1 indicates available entry */
	
	/* preparation for select */
	maxfd = listenfd;			
	FD_ZERO(&allset);
	FD_SET(listenfd, &allset);

	for ( ; ; ) 
	{
		rset = allset;		/* structure assignment */
		if( (nready=select(maxfd+1, &rset, NULL, NULL, NULL))<0)
		{
			perror("Problem with select()");
			exit(-1);
		}
		
		/* new client connection */
		if (FD_ISSET(listenfd, &rset)) 
		{	
			
			clilen = sizeof(cliaddr);
			if( (connfd = accept(listenfd, (struct sockaddr*) &cliaddr, &clilen))<0)
			{
				perror("Error in accept()");
				continue;
			}
			
			
			if(inet_ntop(AF_INET,&(cliaddr.sin_addr.s_addr),clientip,INET_ADDRSTRLEN)==NULL)
			{
				perror("inet_ntop()");
				continue;
			}
			
			printf("New client: %s, port %d\n",
					clientip,
					ntohs(cliaddr.sin_port));

			for (i = 0; i < FD_SETSIZE; i++)
				if (client[i] < 0) 
				{
					/* save descriptor */
					client[i] = connfd;
					
					/*save IP address*/
					clientiparray[i]=(char*)malloc(INET_ADDRSTRLEN);
					strcpy(clientiparray[i],clientip);	
					
					break;
				}
			
			if (i == FD_SETSIZE)
			{
				fprintf(stderr,"Too many clients\n");
				/* duh...we could consider*/
				/* being a little more */
				/* sophisticated :-)   */
				exit(1);
			}
			

			/* add new descriptor to set */
			FD_SET(connfd, &allset);	
			if (connfd > maxfd)
				maxfd = connfd;	/* for select */
			if (i > maxi)
				maxi = i;	/* max index in client[] array */

			if (--nready <= 0)
				continue;	/* no more readable descriptors */
		}/*End of actions for new connection*/

		/* check all clients for data */
		for (i = 0; i <= maxi; i++) 
		{	
			if ( (sockfd = client[i]) < 0)
				continue;
			if (FD_ISSET(sockfd, &rset)) 
			{
				if ( (n = Readline(sockfd, line, MAXLINE)) <= 0) 
				{
					if(n==0) 
					{
						/*connection closed by client */
						close(sockfd);
						FD_CLR(sockfd, &allset);
						client[i] = -1;
					
						printf("Client %s closed the connection\n",clientiparray[i]);	
						free(clientiparray[i]);
					}
					else 
					{
						perror("Problem in Readline()");
						/*We can be more sophisticated here, but
						  we'll follow KISS for now*/
						
						close(sockfd);
						FD_CLR(sockfd, &allset);
						client[i] = -1;
					
						free(clientiparray[i]);
						/*We don't exit*/
					}
					
				} 
				else 
				{
								printf("Sending the following string to client %s:\n%s",clientiparray[i],line);
								Writen(sockfd, line, n);
				}

				if (--nready <= 0)
					break;	/* no more readable descriptors */
			}
		}
	}
}
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t n, rc;
	char c, *ptr;

	ptr = vptr;
	for(n = 1; n < maxlen; n++)
	{
	
			if((rc = read(fd, &c, 1)) == 1)
			{
				*ptr++ = c;
				if(c == '\n')
					break;
			}
			else if(rc == 0)
			{
				if(n == 1)
					return 0;
				else
					break;
			}
			else
			{
				if(errno == EINTR)
					continue;
				return (-1);
			}
	}
	*ptr = 0;
	return n;
}
/*----------------------------------------*/
ssize_t Writen(int fd, const void *vptr, size_t n)
{
	size_t nleft;
	ssize_t nwritten;
	const char *ptr;

	ptr = vptr;
	nleft = n;

	while(nleft > 0)
	{
		if ((nwritten = write(fd, ptr, nleft)) <= 0)
		{	
			if (errno == EINTR)
				nwritten = 0;
			else
				return -1;
		}

		nleft -= nwritten;
		ptr += nwritten;
	}

	return n;
}
/****************************************************************/
/*simple echo client, using I/O multiplexing, but with a defect */
#include <stdio.h>
#include <netinet/in.h>  
#include <arpa/inet.h>   
#include <sys/types.h>   
#include <sys/socket.h>  
#include <unistd.h>      
#include <strings.h>     
#include <string.h>      
#include <errno.h>       
#include <stdlib.h>      
#include <sys/select.h>  

#define max(a,b) (((a)>(b))?(a):(b))

#define PORT  7
#define MAXSIZE 1024
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen);
ssize_t Writen(int fd, const void *vptr, size_t n);
/*----------------------------------------*/
int  main(int argc, char* argv[])
{
	int sockfd;
	struct sockaddr_in servaddr,cliaddr;
	int ret;
	char sendbuf[MAXSIZE],recvbuf[MAXSIZE];
	int scount,strsize,ch;
	
	/*Select() stuff*/
	
	int maxfdp;
	fd_set rset,allset;
	int selret;

	if(argc<2)
	{
		fprintf(stderr,"Usage: echoclient IPaddr [port]\n");
		exit(-1);
	}

	if( (sockfd=socket(AF_INET,SOCK_STREAM,0)) < 0)
	{
		perror("Problem with socket()");
		exit(-1);
	}

	
	/*Server address initialization*/
	
	bzero(&servaddr,sizeof(servaddr));
	
	servaddr.sin_family=AF_INET;

	if( inet_pton(AF_INET,argv[1],&(servaddr.sin_addr))<=0)
	{
		fprintf(stderr,"Improper address or family\n");
		exit(-1);
	}
		
	if(argc>2)
		servaddr.sin_port=htons(atoi(argv[2]));
	else
		servaddr.sin_port=htons(PORT);

	
	if( (connect(sockfd,(struct sockaddr*)&servaddr,sizeof(servaddr))) < 0)
	{
		perror("Error in connect()");
		exit(-1);
	}
	

	/*Initialization*/	
	FD_ZERO(&allset);
	FD_SET(sockfd,&allset);
	FD_SET(fileno(stdin),&allset);
	maxfdp=max(fileno(stdin),sockfd);
	
	for(;;)
	{

		rset=allset;
		
		if( (selret=select(maxfdp+1,&rset,NULL,NULL,NULL)) < 0)
		{
			perror("Problem with select()");
			exit(-1);
		}
		
		/*check if the socket is readable*/

		if(FD_ISSET(sockfd,&rset))
		{	
			if(Readline(sockfd,recvbuf,MAXSIZE)==0)
			{
				fprintf(stderr,"Server terminated prematurely\n");
				exit(-1);
			}
			else
				fputs(recvbuf,stdout);
		}	
		
		/*check if input is readable*/

		if(FD_ISSET(fileno(stdin),&rset))
		{
		
			if( fgets(sendbuf,MAXSIZE,stdin)==NULL)
				return;
			else
				Writen(sockfd,sendbuf,strlen(sendbuf));
		}//end if
	}//end for(;;)
	close(sockfd);
}
/*----------------------------------------*/

ssize_t Readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t n, rc;
	char c, *ptr;

	ptr = vptr;
	for(n = 1; n < maxlen; n++)
	{
			if((rc = read(fd, &c, 1)) == 1)
			{
				*ptr++ = c;
				if(c == '\n')
					break;
			}
			else if(rc == 0)
			{
				if(n == 1)
					return 0;
				else
					break;
			}
			else
			{
				if(errno == EINTR)
					continue;
				perror("Error in read()");
				return (-1);
			}
	}
	*ptr = 0;
	return n;
}
/*----------------------------------------*/
ssize_t Writen(int fd, const void *vptr, size_t n)
{
	size_t nleft;
	ssize_t nwritten;
	const char *ptr;

	ptr = vptr;
	nleft = n;

	while(nleft > 0)
	{
		if ((nwritten = write(fd, ptr, nleft)) <= 0)
		{	
			if (errno == EINTR)
				nwritten = 0;
			else
			{	
				perror("Error in write()");
				return -1;
			}
		}

		nleft -= nwritten;
		ptr += nwritten;
	}

	return n;
}
/****************************************************************/
/* simple echo client using I/O multiplexing */
#include <stdio.h>
#include <netinet/in.h>  
#include <arpa/inet.h>   
#include <sys/types.h>   
#include <sys/socket.h>  
#include <unistd.h>      
#include <strings.h>     
#include <string.h>      
#include <errno.h>       
#include <stdlib.h>      
#include <sys/select.h>  
/*----------------------------------------*/
#define max(a,b) (((a)>(b))?(a):(b))
#define PORT  7
#define MAXSIZE 1024
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen);
ssize_t Writen(int fd, const void *vptr, size_t n);
/*----------------------------------------*/
int  main(int argc, char* argv[])
{
	int sockfd;
	struct sockaddr_in servaddr,cliaddr;
	int ret;
	char sendbuf[MAXSIZE],recvbuf[MAXSIZE];
	int scount,strsize,ch;
	
	/*Select() stuff*/
	
	int maxfdp, stdineof;
	fd_set rset,allset;
	int selret;

	if(argc<2)
	{
		fprintf(stderr,"Usage: echoclient IPaddr [port]\n");
		exit(-1);
	}

	if( (sockfd=socket(AF_INET,SOCK_STREAM,0)) < 0)
	{
		perror("Problem with socket()");
		exit(-1);
	}

	
	/*Server address initialization*/
	
	bzero(&servaddr,sizeof(servaddr));
	
	servaddr.sin_family=AF_INET;

	if( inet_pton(AF_INET,argv[1],&(servaddr.sin_addr))<=0)
	{
		fprintf(stderr,"Improper address or family\n");
		exit(-1);
	}
		
	if(argc>2)
		servaddr.sin_port=htons(atoi(argv[2]));
	else
		servaddr.sin_port=htons(PORT);

	
	if( (connect(sockfd,(struct sockaddr*)&servaddr,sizeof(servaddr))) < 0)
	{
		perror("Error in connect()");
		exit(-1);
	}
	

	/*Initialization*/	
	FD_ZERO(&allset);
	FD_SET(sockfd,&allset);
	FD_SET(fileno(stdin),&allset);
	maxfdp=max(fileno(stdin),sockfd);
	
	stdineof=0;
	
	for(;;)
	{

		rset=allset;
		
		if( (selret=select(maxfdp+1,&rset,NULL,NULL,NULL)) < 0)
		{
			perror("Problem with select()");
			exit(-1);
		}
		
		/*check if the socket is readable*/

		if(FD_ISSET(sockfd,&rset))
		{	
			if(Readline(sockfd,recvbuf,MAXSIZE)==0)
			{
				if(stdineof==1)
					return; /*Normal termination*/
				else
				{
					
					fprintf(stderr,"Server terminated prematurely\n");
					exit(-1);
				}
			}
			
			else 
				fputs(recvbuf,stdout);
		}	
		
		/*check if input is readable*/

		if(FD_ISSET(fileno(stdin),&rset))
		{
		
			if( fgets(sendbuf,MAXSIZE,stdin)==NULL)
			{
				stdineof=1;
				
				if(shutdown(sockfd,SHUT_WR)<0)
				{
					perror("Error in shutdown");
					exit(-1);
				}
				
				FD_CLR(fileno(stdin),&allset);
				maxfdp=sockfd;
			}
			
			else
			Writen(sockfd,sendbuf,strlen(sendbuf));
		}//end if
	}//end for(;;)
	close(sockfd);
}
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t n, rc;
	char c, *ptr;

	ptr = vptr;
	for(n = 1; n < maxlen; n++)
	{
	
			if((rc = read(fd, &c, 1)) == 1)
			{
				*ptr++ = c;
				if(c == '\n')
					break;
			}
			else if(rc == 0)
			{
				if(n == 1)
					return 0;
				else
					break;
			}
			else
			{
				if(errno == EINTR)
					continue;
				perror("Error in read()");
				return (-1);
			}
	}
	*ptr = 0;
	return n;
}
/*----------------------------------------*/
ssize_t Writen(int fd, const void *vptr, size_t n)
{
	size_t nleft;
	ssize_t nwritten;
	const char *ptr;

	ptr = vptr;
	nleft = n;

	while(nleft > 0)
	{
		if ((nwritten = write(fd, ptr, nleft)) <= 0)
		{	
			if (errno == EINTR)
				nwritten = 0;
			else
			{	
				perror("Error in write()");
				return -1;
			}
		}

		nleft -= nwritten;
		ptr += nwritten;
	}

	return n;
}
/****************************************************************/
/* 5_options.c */
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
int main()
{

	int sock_fd;
	int value;
	int size=sizeof(value);
	if((sock_fd=socket(AF_INET,SOCK_STREAM,0)) <0 )
	{
		/*-- Cant get the socket API --*/
		perror("Error opening socket..\n");
		exit(-1);
	}
	/*--- check for generic options ---*/
	if(getsockopt(sock_fd,SOL_SOCKET,SO_SNDBUF,&value,&size) == -1) 
	{	
		perror("getsockopt error");
		exit(-1);
	}
	printf("Size of send buffer is %d\n",value);
}
/****************************************************************/
/* Experimenting with socket options */
/*----------------------------------------*/
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include <netinet/tcp.h>
#include <netinet/in.h>
/*----------------------------------------*/
union val 
{
  int			i_val;
  long			l_val;
  char			c_val[10];
  struct linger		linger_val;
  struct timeval	timeval_val;
} val;
/*----------------------------------------*/
static char	*sock_str_flag(union val *, int);
static char	*sock_str_int(union val *, int);
static char	*sock_str_linger(union val *, int);
static char	*sock_str_timeval(union val *, int);
/*----------------------------------------*/
struct sock_opts
 {
  char	*opt_str;
  int	opt_level;
  int	opt_name;
  char  *(*opt_val_str)(union val *, int);
} sock_opts[] = 
	{
			"SO_BROADCAST",		SOL_SOCKET,	SO_BROADCAST,	sock_str_flag,
			"SO_DEBUG",		SOL_SOCKET,	SO_DEBUG,	sock_str_flag,
			"SO_DONTROUTE",		SOL_SOCKET,	SO_DONTROUTE,	sock_str_flag,
			"SO_ERROR",		SOL_SOCKET,	SO_ERROR,	sock_str_int,
			"SO_KEEPALIVE",		SOL_SOCKET,	SO_KEEPALIVE,	sock_str_flag,
			"SO_LINGER",		SOL_SOCKET,	SO_LINGER,	sock_str_linger,
			"SO_OOBINLINE",		SOL_SOCKET,	SO_OOBINLINE,	sock_str_flag,
			"SO_RCVBUF",		SOL_SOCKET,	SO_RCVBUF,	sock_str_int,
			"SO_SNDBUF",		SOL_SOCKET,	SO_SNDBUF,	sock_str_int,
			"SO_RCVLOWAT",		SOL_SOCKET,	SO_RCVLOWAT,	sock_str_int,
			"SO_SNDLOWAT",		SOL_SOCKET,	SO_SNDLOWAT,	sock_str_int,
			"SO_RCVTIMEO",		SOL_SOCKET,	SO_RCVTIMEO,	sock_str_timeval,
			"SO_SNDTIMEO",		SOL_SOCKET,	SO_SNDTIMEO,	sock_str_timeval,
			"SO_REUSEADDR",		SOL_SOCKET,	SO_REUSEADDR,	sock_str_flag,
		#ifdef	SO_REUSEPORT
			"SO_REUSEPORT",		SOL_SOCKET,	SO_REUSEPORT,	sock_str_flag,
		#else
			"SO_REUSEPORT",		0,		0,		NULL,
		#endif
			"SO_TYPE",		SOL_SOCKET,	SO_TYPE,	sock_str_int,
		#ifdef  SO_USELOOPBACK
			"SO_USELOOPBACK",	SOL_SOCKET,	SO_USELOOPBACK,	sock_str_flag,
		#else
			"SO_USELOOPBACK",	0,		0,		NULL,
		#endif
			"IP_TOS",		IPPROTO_IP,	IP_TOS,		sock_str_int,
			"IP_TTL",		IPPROTO_IP,	IP_TTL,		sock_str_int,
			"TCP_MAXSEG",		IPPROTO_TCP,TCP_MAXSEG,		sock_str_int,
			"TCP_NODELAY",		IPPROTO_TCP,TCP_NODELAY,	sock_str_flag,
			NULL,			0,			0,	NULL
	};
/*----------------------------------------*/
int main(int argc, char **argv)
{
	int fd;
	socklen_t len;
	struct sock_opts *ptr;

	fd = socket(AF_INET, SOCK_STREAM, 0);

	for (ptr = sock_opts; ptr->opt_str != NULL; ptr++)
	{
		printf("%s: ", ptr->opt_str);
		if (ptr->opt_val_str == NULL)
			printf("(undefined)\n");
		else 
		{
			len = sizeof(val);
			if (getsockopt(fd, ptr->opt_level, ptr->opt_name,
						   &val, &len) == -1)
			{
				fprintf(stderr,"Problem with getsockopt(): %s\n",strerror(errno));

			} else 
			{
				printf("default = %s\n", (*ptr->opt_val_str)(&val, len));
			}
		}
	}
	exit(0);
}
/*----------------------------------------*/
static char	strres[128];
/*----------------------------------------*/
static char	*sock_str_flag(union val *ptr, int len)
{

	if (len != sizeof(int))
		snprintf(strres, sizeof(strres), "size (%d) not sizeof(int)", len);
	else
		snprintf(strres, sizeof(strres),
				 "%s", (ptr->i_val == 0) ? "off" : "on");
	return(strres);

}
/*----------------------------------------*/
static char	*sock_str_int(union val *ptr, int len)
{
	if (len != sizeof(int))
		snprintf(strres, sizeof(strres), "size (%d) not sizeof(int)", len);
	else
		snprintf(strres, sizeof(strres), "%d", ptr->i_val);
	return(strres);
}
/*----------------------------------------*/
static char	*sock_str_linger(union val *ptr, int len)
{
	struct linger	*lptr = &ptr->linger_val;

	if (len != sizeof(struct linger))
		snprintf(strres, sizeof(strres),
				 "size (%d) not sizeof(struct linger)", len);
	else
		snprintf(strres, sizeof(strres), "l_onoff = %d, l_linger = %d",
				 lptr->l_onoff, lptr->l_linger);
	return(strres);
}
/*----------------------------------------*/
static char	*sock_str_timeval(union val *ptr, int len)
{
	struct timeval	*tvptr = &ptr->timeval_val;

	if (len != sizeof(struct timeval))
		snprintf(strres, sizeof(strres),
				 "size (%d) not sizeof(struct timeval)", len);
	else
		snprintf(strres, sizeof(strres), "%d sec, %d usec",
				 tvptr->tv_sec, tvptr->tv_usec);
	return(strres);
}
/****************************************************************/
/* simple echo client which sends an RST on close */
/*----------------------------------------*/
#include <stdio.h>
#include <netinet/in.h>  
#include <arpa/inet.h>   
#include <sys/types.h>   
#include <sys/socket.h>  
#include <unistd.h>      
#include <strings.h>     
#include <string.h>      
#include <errno.h>       
#include <stdlib.h>      
#include <sys/select.h>  
/*----------------------------------------*/
#define max(a,b) (((a)>(b))?(a):(b))
/*----------------------------------------*/
#define PORT  7
#define MAXSIZE 1024
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen);
ssize_t Writen(int fd, const void *vptr, size_t n);
/*----------------------------------------*/
int  main(int argc, char* argv[])
{
	int sockfd;
	struct sockaddr_in servaddr,cliaddr;
	int ret;
	char sendbuf[MAXSIZE],recvbuf[MAXSIZE];
	int scount,strsize,ch;

	/*Bad boy stuff here*/
	struct linger ln;
	
	/*Select() stuff*/
	
	int maxfdp, stdineof;
	fd_set rset,allset;
	int selret;

	if(argc<2)
	{
		fprintf(stderr,"Usage: echoclient IPaddr [port]\n");
		exit(-1);
	}
	if( (sockfd=socket(AF_INET,SOCK_STREAM,0)) < 0)
	{
		perror("Problem with socket()");
		exit(-1);
	}

	/*Server address initialization*/
	
	bzero(&servaddr,sizeof(servaddr));
	
	servaddr.sin_family=AF_INET;

	if( inet_pton(AF_INET,argv[1],&(servaddr.sin_addr))<=0)
	{
		fprintf(stderr,"Improper address or family\n");
		exit(-1);
	}
		
	if(argc>2)
		servaddr.sin_port=htons(atoi(argv[2]));
	else
		servaddr.sin_port=htons(PORT);

	
	if( (connect(sockfd,(struct sockaddr*)&servaddr,sizeof(servaddr))) < 0)
	{
		perror("Error in connect()");
		exit(-1);
	}
	
	/*Initialization for RST*/
	ln.l_onoff=1;
	ln.l_linger=0;

	if( setsockopt(sockfd,SOL_SOCKET,SO_LINGER,&ln,sizeof(ln))<0) 
	{
			perror("Problem with setsockopt()");
			exit(1);
	}

	/*Initialization*/	
	FD_ZERO(&allset);
	FD_SET(sockfd,&allset);
	FD_SET(fileno(stdin),&allset);
	maxfdp=max(fileno(stdin),sockfd);
	
	stdineof=0;
	
	for(;;)
	{

		rset=allset;
		
		if( (selret=select(maxfdp+1,&rset,NULL,NULL,NULL)) < 0)
		{
			perror("Problem with select()");
			exit(-1);
		}
		
		/*check if the socket is readable*/

		if(FD_ISSET(sockfd,&rset))
		{	
			if(Readline(sockfd,recvbuf,MAXSIZE)==0)
			{
				fprintf(stderr,"Server terminated prematurely\n");
				exit(-1);
			}
			
			else
			       	fputs(recvbuf,stdout);
		}	
		
		/*check if input is readable*/

		if(FD_ISSET(fileno(stdin),&rset))
		{
		
			if( fgets(sendbuf,MAXSIZE,stdin)==NULL)
			{
				close(sockfd);
				return;
			}
			else
				Writen(sockfd,sendbuf,strlen(sendbuf));
		}//end if
	}//end for(;;)
	close(sockfd);
}
/*----------------------------------------*/
ssize_t Readline(int fd, void *vptr, size_t maxlen)
{
	ssize_t n, rc;
	char c, *ptr;

	ptr = vptr;
	for(n = 1; n < maxlen; n++)
	{
			if((rc = read(fd, &c, 1)) == 1)
			{
				*ptr++ = c;
				if(c == '\n')
					break;
			}
			else if(rc == 0)
			{
				if(n == 1)
					return 0;
				else
					break;
			}
			else
			{
				if(errno == EINTR)
					continue;
				perror("Error in read()");
				return (-1);
			}
	}
	*ptr = 0;
	return n;
}
/*----------------------------------------*/
ssize_t Writen(int fd, const void *vptr, size_t n)
{
	size_t nleft;
	ssize_t nwritten;
	const char *ptr;

	ptr = vptr;
	nleft = n;

	while(nleft > 0)
	{
		if ((nwritten = write(fd, ptr, nleft)) <= 0)
		{	
			if (errno == EINTR)
				nwritten = 0;
			else
			{	
				perror("Error in write()");
				return -1;
			}
		}

		nleft -= nwritten;
		ptr += nwritten;
	}

	return n;
}
/****************************************************************/
/* mrecver.c */
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <time.h>
#include <string.h>
#include <stdio.h>
/*----------------------------------------*/
#define HELLO_PORT 1245
#define HELLO_GROUP "225.0.0.37"
#define MSGBUFSIZE 10
/*----------------------------------------*/
int main(int argc, char *argv[])
{
     struct sockaddr_in addr;
     int fd, nbytes,addrlen;
     struct ip_mreq mreq;
     char msgbuf[MSGBUFSIZE];

     u_int yes=1;            

     /* create what looks like an ordinary UDP socket */
     if ((fd=socket(AF_INET,SOCK_DGRAM,0)) < 0) 
	 {
		perror("socket");
		exit(1);
     }


    /* allow multiple sockets to use the same PORT number */
    if (setsockopt(fd,SOL_SOCKET,SO_REUSEADDR,&yes,sizeof(yes)) < 0)
	{
       perror("Reusing ADDR failed");
       exit(1);
    }

     /* set up destination address */
     memset(&addr,0,sizeof(addr));
     addr.sin_family=AF_INET;
     addr.sin_addr.s_addr=htonl(INADDR_ANY); /* N.B.: differs from sender */
     addr.sin_port=htons(HELLO_PORT);
     
     /* bind to receive address */
     if (bind(fd,(struct sockaddr *) &addr,sizeof(addr)) < 0) 
	 {
		perror("bind");
		exit(1);
     }
     
     /* use setsockopt() to request that the kernel join a multicast group */
     mreq.imr_multiaddr.s_addr=inet_addr(HELLO_GROUP);
     mreq.imr_interface.s_addr=htonl(INADDR_ANY);
     if (setsockopt(fd,IPPROTO_IP,IP_ADD_MEMBERSHIP,&mreq,sizeof(mreq)) < 0) 
	 {
		perror("setsockopt");
		exit(1);
     }

     /* read-print loop */
     while (1) 
	 {
		addrlen=sizeof(addr);
		memset(msgbuf,'\0',MSGBUFSIZE);
		if ((nbytes=recvfrom(fd,msgbuf,MSGBUFSIZE,0,
			       (struct sockaddr *) &addr,&addrlen)) < 0) 
		{
	       perror("recvfrom");
	       exit(1);
		}
	 	if(strncmp(msgbuf,"quit",4) == 0 )
		{
		 	printf("Sender finished\n");
			break;
		}
		puts(msgbuf);
     }
	 return 0;
}
/****************************************************************/
/* msender.c */
#include "NP.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <time.h>
#include <string.h>
#include <stdio.h>
/*----------------------------------------*/
#define HELLO_PORT 1245
#define HELLO_GROUP "225.0.0.37"
#define MSGSIZE 10
/*----------------------------------------*/
int main(int argc, char *argv[])
{
     struct sockaddr_in addr;
     int fd, cnt,len;
     struct ip_mreq mreq;
     char message[MSGSIZE];

     /* create what looks like an ordinary UDP socket */
     if ((fd=socket(AF_INET,SOCK_DGRAM,0)) < 0) 
	 {
		perror("socket");
		exit(1);
     }
     /* set up destination address */
     memset(&addr,0,sizeof(addr));
     addr.sin_family=AF_INET;
     addr.sin_addr.s_addr=inet_addr(HELLO_GROUP);
     addr.sin_port=htons(HELLO_PORT);
     
     /* now just sendto() our destination! */
     while (1)
	  {
			memset(message,'\0',MSGSIZE);
			scanf("%s",message);
			cnt=sendto(fd,message,strlen(message),0,(struct sockaddr *) &addr, sizeof(addr));
			if (cnt < 0) 
			{
			   perror("sendto not successful");
			   exit(1);
			}
			printf("%d characters send\n",cnt);
			if(strncmp(message,"quit",4) == 0 ) 
				break;
     }
	  printf("Goddbye\n");
}
/****************************************************************/
/* ueClient1.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>

void recvfrom_alarm(int signo)
{
	return;
}

int main(int argc, char **argv)
{
	const int on =1;
   int sockfd;
   struct sockaddr_in servaddr,their_addr;
   int len,n,their_len;
   char recvline[MAXLINE],sendline[MAXLINE];


   if (argc != 2)
   {
      perror("Usage : ECHCLIENT <IPAddress>");
      exit(1);
   }

   if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
       perror("socket");
       exit(1);
   }
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

   bzero(&servaddr,sizeof(servaddr));

   servaddr.sin_family = AF_INET;
   servaddr.sin_port = htons(SERV_PORT);

   inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);
	
	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
   {
      if(strncmp(sendline,"quit",4) == 0 )
   	      break;

		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);

		len = sizeof(their_addr);
		alarm(8);
		
		for(; ;)
		{
			their_len=len;
			n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 
			if( n < 0 )
			{
				if(errno == EINTR )
					break;
				else
					perror("recvfrom error");
			}
			else
			{
				recvline[n]=0;
				printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
			}

		}
	}
	free(their_addr);
			  
        return(0);
}
/****************************************************************/
/* ueClient2.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
void recvfrom_alarm(int signo)
{
	return;
}
int main(int argc, char **argv)
{
	const int on =1;
   int sockfd;
   struct sockaddr_in servaddr,their_addr;
   int len,n,their_len;
   char recvline[MAXLINE],sendline[MAXLINE];

	sigset_t sigset_alrm;

   if (argc != 2)
   {
      perror("Usage : ECHCLIENT <IPAddress>");
      exit(1);
   }

   if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1)
   {
       perror("socket");
       exit(1);
   }
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

   bzero(&servaddr,sizeof(servaddr));

   servaddr.sin_family = AF_INET;
   servaddr.sin_port = htons(SERV_PORT);
   inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);
	
	sigemptyset(&sigset_alrm);
	sigaddset(&sigset_alrm, SIGALRM);

	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
   {
      if(strncmp(sendline,"quit",4) == 0 )
   	      break;

		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);

		len = sizeof(their_addr);
		alarm(1);
		
		for(; ;)
		{
			their_len=len;
			//sleep(1);	
			sigprocmask(SIG_UNBLOCK, &sigset_alrm, NULL);

			n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 

			sigprocmask(SIG_BLOCK, &sigset_alrm, NULL);

			if( n < 0 )
			{
				if(errno == EINTR )
					break;
				else
					perror("recvfrom error");
			}
			else
			{
				recvline[n]=0;
				sleep(1);
				printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
			}

		}
	}
	free(their_addr);
			  
        return(0);
}
/****************************************************************/
/* ueClient3.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
void recvfrom_alarm(int signo)
{
	return;
}
int main(int argc, char **argv)
{
	const int on =1;
   int sockfd;
   struct sockaddr_in servaddr,their_addr;
   int len,n,their_len;
   char recvline[MAXLINE],sendline[MAXLINE];

	sigset_t sigset_alrm;

   if (argc != 2)
   {
      perror("Usage : ECHCLIENT <IPAddress>");
      exit(1);
   }

   if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1)
   {
       perror("socket");
       exit(1);
   }
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

   bzero(&servaddr,sizeof(servaddr));

   servaddr.sin_family = AF_INET;
   servaddr.sin_port = htons(SERV_PORT);
   inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);
	
	sigemptyset(&sigset_alrm);
	sigaddset(&sigset_alrm, SIGALRM);

	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
    {
      if(strncmp(sendline,"quit",4) == 0 )
   	      break;

		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);

		len = sizeof(their_addr);
		alarm(1);
		
		for(; ;)
		{
			their_len=len;
			//sleep(1);	
			sigprocmask(SIG_UNBLOCK, &sigset_alrm, NULL);

			n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 

			sigprocmask(SIG_BLOCK, &sigset_alrm, NULL);

			if( n < 0 )
			{
				if(errno == EINTR )
					break;
				else
					perror("recvfrom error");
			}
			else
			{
				recvline[n]=0;
				sleep(1);
				printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
			}

		}
	}
	free(their_addr);
    return(0);
}
/****************************************************************/
/* ueClient4.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
void recvfrom_alarm(int signo)
{
	return;
}
int main(int argc, char **argv)
{
	const int on =1;
   int sockfd;
   struct sockaddr_in servaddr,their_addr;
   int len,n,their_len,m;
   char recvline[MAXLINE],sendline[MAXLINE];
	
	fd_set rset;

	sigset_t sigset_empty, sigset_alrm;

   if (argc != 2)
   {
      perror("Usage : ECHCLIENT <IPAddress>");
      exit(1);
   }

   if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) 
   {
       perror("socket");
       exit(1);
   }
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

   bzero(&servaddr,sizeof(servaddr));

   servaddr.sin_family = AF_INET;
   servaddr.sin_port = htons(SERV_PORT);
   inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);

	FD_ZERO(&rset);

	sigemptyset(&sigset_empty);
	sigemptyset(&sigset_alrm);
	sigaddset(&sigset_alrm, SIGALRM);

	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
   {
      if(strncmp(sendline,"quit",4) == 0 )
   	      break;

		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);
		len = sizeof(their_addr);

		sigprocmask(SIG_BLOCK, &sigset_alrm, NULL);
		alarm(5);
		
		for(; ;)
		{
			FD_SET(sockfd,&rset);
			m = pselect(sockfd+1,&rset,NULL,NULL,NULL,&sigset_empty);
			if(m < 0)
			{
				if(errno == EINTR)
					break;
				else
					perror("pselect error");
			}
			else if(m != 1)
				perror("pselect error ");
			
			their_len=len;

			sigprocmask(SIG_UNBLOCK, &sigset_alrm, NULL);

			n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 

			if( n < 0 )
			{
				if(errno == EINTR )
					break;
				else
					perror("recvfrom error");
			}
			else
			{
				recvline[n]=0;
				sleep(1);
				printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
			}

		}
	}
	free(their_addr);
	return(0);
}
/****************************************************************/
/* ueClient5.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
#include <setjmp.h>
static sigjmp_buf jmpbuf;
static void recvfrom_alarm(int signo)
{
//	return;
	siglongjmp(jmpbuf,1);
}
int main(int argc, char **argv)
{
	const int on =1;
   int sockfd;
   struct sockaddr_in servaddr,their_addr;
   int len,n,their_len;
   char recvline[MAXLINE],sendline[MAXLINE];

	sigset_t sigset_alrm;

   if (argc != 2)
   {
      perror("Usage : ECHCLIENT <IPAddress>");
      exit(1);
   }

   if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) 
   {
       perror("socket");
       exit(1);
   }
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

   bzero(&servaddr,sizeof(servaddr));

   servaddr.sin_family = AF_INET;
   servaddr.sin_port = htons(SERV_PORT);
   inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);
	
	sigemptyset(&sigset_alrm);
	sigaddset(&sigset_alrm, SIGALRM);

	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
    {
      if(strncmp(sendline,"quit",4) == 0 )
   	      break;

		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);

		len = sizeof(their_addr);
		alarm(5);
		
		for(; ;)
		{
			if( sigsetjmp(jmpbuf, 1) != 0)
				break;

			their_len=len;


			n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 

			sigprocmask(SIG_BLOCK, &sigset_alrm, NULL);

			if( n < 0 )
			{
				if(errno == EINTR )
					break;
				else
					perror("recvfrom error");
			}
			else
			{
				recvline[n]=0;
				sleep(1);
				printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
			}
			sigprocmask(SIG_UNBLOCK, &sigset_alrm, NULL);

		}
	}
	free(their_addr);
	return(0);
}
/****************************************************************/
/* ueClient6.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
#define max(a,b) ( (a) < (b) ? (a) : (b) )
static pipefd[2];
void recvfrom_alarm(int signo)
{
	write(pipefd[1],"",1);		//write one NULL byte to pipe
	return;
}
int main(int argc, char **argv)
{
	const int on =1;
	int sockfd;
	struct sockaddr_in servaddr,their_addr;
	int len,n,their_len,m,maxfdp1;
	char recvline[MAXLINE],sendline[MAXLINE];

	fd_set rset;

	//sigset_t sigset_empty, sigset_alrm;

	if (argc != 2)
	{
	  perror("Usage : ECHCLIENT <IPAddress>");
	  exit(1);
	}

	if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1)
	{
	   perror("socket");
	   exit(1);
	}
	setsockopt(sockfd, SOL_SOCKET, SO_BROADCAST, &on, sizeof(on) );

	bzero(&servaddr,sizeof(servaddr));

	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(SERV_PORT);
	inet_pton(AF_INET, argv[1],&servaddr.sin_addr);

	len=sizeof(servaddr);

	pipe(pipefd);
	maxfdp1 = max(sockfd,pipefd[0]) + 1;

	FD_ZERO(&rset);

	//sigemptyset(&sigset_empty);
	//sigemptyset(&sigset_alrm);
	//sigaddset(&sigset_alrm, SIGALRM);

	signal(SIGALRM,recvfrom_alarm);

	while( fgets( sendline, MAXLINE, stdin) != NULL)
	{
		if(strncmp(sendline,"quit",4) == 0 )
		  break;
		sendto(sockfd, sendline, strlen(sendline),0, (struct sockaddr *)& servaddr, len);
		len = sizeof(their_addr);
		//	sigprocmask(SIG_BLOCK, &sigset_alrm, NULL);
		alarm(5);

		for(; ;)
		{
			FD_SET(sockfd,&rset);
			FD_SET(pipefd[0], &rset);
			m = select(sockfd+1,&rset,NULL,NULL,NULL);
			if(m < 0)
			{
				if(errno == EINTR)
					break;
				else
					perror("select error");
			}
			if(FD_ISSET(sockfd, &rset))
			{
				their_len=len;

				//sigprocmask(SIG_UNBLOCK, &sigset_alrm, NULL);

				n = recvfrom(sockfd, recvline, MAXLINE, 0, (struct sockaddr *)&their_addr, &their_len); 

				if( n < 0 )
				{
					if(errno == EINTR )
						break;
					else
						perror("recvfrom error");
				}
				else
				{
					recvline[n]=0;
					sleep(1);
					printf("from %s : %s",inet_ntoa(their_addr.sin_addr) ,recvline);
				}
			 }

			 if( FD_ISSET(pipefd[0], &rset))
			 {
				read(pipefd[0], &n, 1);			//timer expired
				break;
			 }
		} // for
	}
	free(their_addr);
	return(0);
}
/****************************************************************/
/* ueServer1.c */
#include "NP.h"
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main()
{
	int sockfd, newfd;
	struct sockaddr_in my_addr;
	struct sockaddr_in their_addr;
	int sin_size,their_size;
	int n;
	char line[MAXLINE];
	pid_t cpid;

	if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1) 
	{
	   perror("socket");
	   exit(1);
	}

	bzero(&my_addr,sizeof(my_addr));

	my_addr.sin_family = AF_INET;         // host byte order
	my_addr.sin_port = htons(SERV_PORT);     // short, network byte order
	my_addr.sin_addr.s_addr = htonl(INADDR_ANY); // automatically fill with my IP

	if (bind(sockfd, (struct sockaddr *)&my_addr, sizeof(my_addr)) == -1) 
	{
	   perror("bind");
	   exit(1);
	}
	their_size = sizeof(their_addr);
	for( ; ;)
	{
		sin_size = their_size;
		n = recvfrom(sockfd, line, MAXLINE, 0, (struct sockaddr *)&their_addr, &sin_size);
		sendto(sockfd, line, n, 0, (struct sockaddr *)&their_addr, sin_size);
	}
	return 0;
}
/****************************************************************/
/* traceroute.c */
#include	<stdio.h>      
#include	<stdlib.h>     
#include	<string.h>     
#include	<strings.h>    
#include	<signal.h>
#include	<errno.h>
#include	<sys/types.h>  
#include	<sys/socket.h> 
#include	<arpa/inet.h>  
#include	<netinet/in.h>       
#include	<netdb.h>
#include	<unistd.h>
#include	<netinet/in_systm.h>
#include	<netinet/ip.h>
#include	<netinet/ip_icmp.h>
#include	<netinet/udp.h>
/*----------------------------------------*/
#define	BUFSIZE		1500
/*----------------------------------------*/
struct rec 
{					/* format of outgoing UDP data */
  u_short	rec_seq;			/* sequence number */
  u_short	rec_ttl;			/* TTL packet left with */
  struct timeval	rec_tv;			/* time packet left */
} *rec;
/*----------------------------------------*/
			/* globals */
char	 recvbuf[BUFSIZE];
char	 sendbuf[BUFSIZE];
int	 datalen;			/* #bytes of data, following ICMP header */
char	*host;
u_short	 sport, dport;
int	 nsent;				/* add 1 for each sendto() */
pid_t	 pid;				/* our PID */
int	 probe, nprobes;
int	 sendfd, recvfd;	/* send on UDP sock, read on raw ICMP sock */
int	 ttl, max_ttl;
int	 verbose;
int 	 done;
/*----------------------------------------*/
struct proto 
{
  struct sockaddr	*sasend;	/* sockaddr{} for send, from getaddrinfo */
  struct sockaddr	*sarecv;	/* sockaddr{} for receiving */
  struct sockaddr	*salast;	/* last sockaddr{} for receiving */
  struct sockaddr	*sabind;	/* sockaddr{} for binding source port */
  socklen_t 		salen;		/* length of sockaddr{}s */
} *pr;
/*----------------------------------------*/
struct proto proto_v4 = {NULL, NULL, NULL, NULL, 0};
int		datalen = sizeof(struct rec);	
int		max_ttl = 30;
int		nprobes = 3;
u_short	dport = 32768 + 666;
/*----------------------------------------*/
void * Calloc(size_t n, size_t size)
{
    void    *ptr;

    if ( (ptr = calloc(n, size)) == NULL)
	{
		perror("calloc error");
		exit(1);
	}
    return(ptr);
}
/*----------------------------------------*/
typedef void    Sigfunc(int);
/*----------------------------------------*/
Sigfunc * Signal(int signo, Sigfunc *func)        
{
    Sigfunc *sigfunc;

    if ( (sigfunc = signal(signo, func)) == SIG_ERR) 
	{
            perror("signal error");
			exit(1);
	}
    return(sigfunc);
}
/*----------------------------------------*/
struct hostent *Gethostbyname(const char *name)
{
	struct hostent *hptr;
	if ((hptr = gethostbyname(name)) == NULL)
	{
		fprintf(stderr, "gethostbyname error for host: %s: %s",name, hstrerror(h_errno));
		exit(1);
	}
	return hptr;
}
/*----------------------------------------*/
struct hostent *Gethostbyaddr(const char *name, int len, int type)
{
	struct hostent *hptr;
	if ((hptr = gethostbyaddr(name, len, type)) == NULL)
	{
		fprintf(stderr, "gethostbyaddr error for host: %s: %s",name, hstrerror(h_errno));
		exit(1);
	}
	return hptr;
}
/*----------------------------------------*/
void Gettimeofday(struct timeval *tv, void *foo)
{
    if (gettimeofday(tv, foo) == -1)
	{
        perror("gettimeofday error");
		exit(1);
	}
    return;
}
/*----------------------------------------*/
void Setsockopt(int fd, int level, int optname, const void *optval, socklen_t optlen)
{
    if (setsockopt(fd, level, optname, optval, optlen) < 0)
	{
        perror("setsockopt error");
		exit(1);
	}
}
/*----------------------------------------*/
void Sendto(int fd, const void *ptr, size_t nbytes, int flags,
           const struct sockaddr *sa, socklen_t salen)
{
    if (sendto(fd, ptr, nbytes, flags, sa, salen) != nbytes)
	{
        perror("sendto error");
		exit(1);
	}
}
/*----------------------------------------*/
int Socket(int family, int type, int protocol)
{
    int             n;
    if ( (n = socket(family, type, protocol)) < 0)
	{
        perror("socket error");
		exit(1);
	}
    return(n);
}
/*----------------------------------------*/
void Bind(int fd, const struct sockaddr *sa, socklen_t salen)
{
    if (bind(fd, sa, salen) < 0)
	{
           perror("bind error");
		exit(1);
	}
}
/*----------------------------------------*/
char* Inet_ntop(const struct sockaddr *sa, socklen_t salen)
{
    static char str[128];               
    struct sockaddr_in      *sin = (struct sockaddr_in *) sa;
	if (inet_ntop(AF_INET, &sin->sin_addr, str, sizeof(str)) == NULL)
	{
		perror("Inet_ntop error : ");
		exit(1);
	}	
	return(str);
}
/*----------------------------------------*/
void validate_args(int argc, char **argv)
{
	int c;
	opterr = 0;		/* don't want getopt() writing to stderr */
	while ((c = getopt(argc, argv, "vhm:")) != -1) 
	{
		switch (c) 
		{
		case 'm':
			if ((max_ttl = atoi(optarg)) <= 1) 
			{
				fprintf(stderr, "invalid -m value");
				exit(1);
			}
			break;
		case 'v':
			verbose++;
			break;
		case 'h':
			usage();
			exit(0);
		case '?':
			fprintf(stderr, "unrecognized option: %c\n", c);
			exit(1);
		}
	}
}
/*----------------------------------------*/
int usage()
{
	printf("Usage: traceroute [ -m <maxttl>] [-v ] <hostname>\n");
	printf("       traceroute -h\n");
	printf("-m <maxttl> : Set maximum TTL to maxttl\n");
	printf("-v          : verbose\n");
	printf("-h          : help\n");
	return 0;
}
/*----------------------------------------*/
int sock_cmp_addr(const struct sockaddr *sa1, const struct sockaddr *sa2,
                  socklen_t salen)
{
	return(memcmp( &((struct sockaddr_in *) sa1)->sin_addr,
				   &((struct sockaddr_in *) sa2)->sin_addr,
				   sizeof(struct in_addr)));
}
/*----------------------------------------*/
struct sockaddr_in* host_serv(const char *host)
{
	struct sockaddr_in *addr;
	struct hostent	*hp;
	
	addr = (struct sockaddr_in *)Calloc(1, sizeof (struct sockaddr));
    	bzero(addr, sizeof(struct sockaddr_in));
    	if ((addr->sin_addr.s_addr = inet_addr(host)) == INADDR_NONE) 
		{
        	hp = Gethostbyname(host); 
			addr->sin_family = hp->h_addrtype;
			memcpy(&(addr->sin_addr), hp->h_addr, hp->h_length);
    	}

	return addr;
}
/*----------------------------------------*/
void tv_sub(struct timeval *out, struct timeval *in)
{
	if ( (out->tv_usec -= in->tv_usec) < 0) 
	{	/* out -= in */
		--out->tv_sec;
		out->tv_usec += 1000000;
	}
	out->tv_sec -= in->tv_sec;
}
/*----------------------------------------*/
void sig_alrm(int signo)
{
	return;		/* just interrupt the recvfrom() */
}
/*----------------------------------------*/
int create_send_socket()
{
	int sendfd; 

	sendfd = Socket(pr->sasend->sa_family, SOCK_DGRAM, 0);
	pr->sabind->sa_family = pr->sasend->sa_family;
	sport = (getpid() & 0xffff) | 0x8000;	/* our source UDP port# */
	((struct sockaddr_in*)pr->sabind)->sin_port = htons(sport); 
	((struct sockaddr_in*)pr->sabind)->sin_addr.s_addr = htonl(INADDR_ANY);
	Bind(sendfd, pr->sabind, pr->salen);
	return sendfd;
}
/*----------------------------------------*/
int create_recv_socket()
{
	recvfd = Socket(pr->sasend->sa_family, SOCK_RAW, IPPROTO_ICMP);
	setuid(getuid());	
	return recvfd;
}
/*----------------------------------------*/
void send_udp_data(seq)
{
	rec = (struct rec *) sendbuf;
	rec->rec_seq = seq;
	rec->rec_ttl = ttl;
	Gettimeofday(&rec->rec_tv, NULL);
	((struct sockaddr_in*)pr->sasend)->sin_port = htons(dport + seq);
	Sendto(sendfd, sendbuf, datalen, 0, pr->sasend, pr->salen);
}
/*----------------------------------------*/
char * icmpcode(int code)
{
	switch (code) {
	case  0:	return("network unreachable");
	case  1:	return("host unreachable");
	case  2:	return("protocol unreachable");
	case  3:	return("port unreachable");
	case  4:	return("fragmentation required but DF bit set");
	case  5:	return("source route failed");
	case  6:	return("destination network unknown");
	case  7:	return("destination host unknown");
	case  8:	return("source host isolated (obsolete)");
	case  9:	return("destination network administratively prohibited");
	case 10:	return("destination host administratively prohibited");
	case 11:	return("network unreachable for TOS");
	case 12:	return("host unreachable for TOS");
	case 13:	return("communication administratively prohibited by filtering");
	case 14:	return("host recedence violation");
	case 15:	return("precedence cutoff in effect");
	default:	return("[unknown code]");
	}
}
/*----------------------------------------*/
void print_status(int code, struct timeval *tvrecv)
{
	double rtt;

	if (code == -3)
		printf(" *");		/* timeout, no reply */
	else 
	{
		char *str;
		struct hostent *h;

		if (sock_cmp_addr(pr->sarecv, pr->salast, pr->salen) != 0) 
		{		
			str = Inet_ntop(pr->sarecv, pr->salen);
			if ((h = gethostbyaddr(str, strlen(str)+1, AF_INET)) == NULL) 
				printf(" %s",str);
			else 
				printf(" %s (%s)", h->h_name, str);
			
			
			memcpy(pr->salast, pr->sarecv, pr->salen);
		}
		tv_sub(tvrecv, &rec->rec_tv);
		rtt = tvrecv->tv_sec * 1000.0 + tvrecv->tv_usec / 1000.0;
		printf("  %.3f ms", rtt);

		if (code == -1)		/* port unreachable; at destination */
			done++;
		else if (code >= 0)
			printf(" (ICMP %s)", icmpcode(code));
	}
	fflush(stdout);
}
/*----------------------------------------*/
int recv_icmp_data(int seq, struct timeval *tv)
{
	/*
	 * Return: -3 on timeout
	 *		   -2 on ICMP time exceeded in transit (caller keeps going)
	 *		   -1 on ICMP port unreachable (caller is done)
	 *		 >= 0 return value is some other ICMP unreachable code
	 */

	int				hlen1, hlen2, icmplen;
	socklen_t		len;
	ssize_t			n;
	struct ip		*ip, *hip;
	struct icmp		*icmp;
	struct udphdr	*udp;

	alarm(3);
	for ( ; ; )
	{
		len = pr->salen;
		n = recvfrom(recvfd, recvbuf, sizeof(recvbuf), 0, pr->sarecv, &len);
		if (n < 0) 
		{
			if (errno == EINTR)
				return(-3);		/* alarm expired */
			else 
			{
				perror("recvfrom error");
				exit(1);
			}
		}
		Gettimeofday(tv, NULL);		/* get time of packet arrival */

		ip = (struct ip *) recvbuf;	/* start of IP header */
		hlen1 = ip->ip_hl << 2;		/* length of IP header */
	
		icmp = (struct icmp *) (recvbuf + hlen1); /* start of ICMP header */
		if ( (icmplen = n - hlen1) < 8) 
		{
			fprintf(stderr, "icmplen (%d) < 8", icmplen);
			exit(1);
		}
	
		if (icmp->icmp_type == ICMP_TIMXCEED &&
			icmp->icmp_code == ICMP_TIMXCEED_INTRANS) 
		{
			if (icmplen < 8 + 20 + 8) {
				fprintf(stderr, "icmplen (%d) < 8 + 20 + 8", icmplen);
				exit(1);
			}

			hip = (struct ip *) (recvbuf + hlen1 + 8);
			hlen2 = hip->ip_hl << 2;
			udp = (struct udphdr *) (recvbuf + hlen1 + 8 + hlen2);
 			if (hip->ip_p == IPPROTO_UDP &&
				udp->source == htons(sport) &&
				udp->dest == htons(dport + seq))
				return(-2);		/* we hit an intermediate router */

		} 
		else if (icmp->icmp_type == ICMP_UNREACH)
		{
			if (icmplen < 8 + 20 + 8) 
			{
				fprintf(stderr, "icmplen (%d) < 8 + 20 + 8", icmplen);
				exit(1);
			}

			hip = (struct ip *) (recvbuf + hlen1 + 8);
			hlen2 = hip->ip_hl << 2;
			udp = (struct udphdr *) (recvbuf + hlen1 + 8 + hlen2);
 			if (hip->ip_p == IPPROTO_UDP &&
				udp->source == htons(sport) &&
				udp->dest == htons(dport + seq))
			{
				if (icmp->icmp_code == ICMP_UNREACH_PORT)
					return(-1);	/* have reached destination */
				else
					return(icmp->icmp_code);	/* 0, 1, 2, ... */
			}
		} 
		else if (verbose)
		{
			printf(" (from %s: type = %d, code = %d)\n",
					Inet_ntop(pr->sarecv, pr->salen),
					icmp->icmp_type, icmp->icmp_code);
		}
	}
}
/*----------------------------------------*/
void traceloop()
{
	int seq, code;
	struct timeval tvrecv;

	recvfd = create_recv_socket();
	sendfd = create_send_socket();

	seq = 0;
	done = 0;
	bzero(pr->salast, pr->salen);

	for (ttl = 1; ttl <= max_ttl && done == 0; ttl++)
	{
		Setsockopt(sendfd, IPPROTO_IP, IP_TTL, &ttl, sizeof(int));
		
		printf("%2d  ", ttl);
		fflush(stdout);

		for (probe = 0; probe < nprobes; probe++) 
		{
			++seq;
			send_udp_data(seq);
			code = recv_icmp_data(seq, &tvrecv);
			print_status(code, &tvrecv);
		}

		printf("\n");
	}
}
/*----------------------------------------*/
int main(int argc, char* argv[])
{
	struct sockaddr_in* addr;	
	validate_args(argc, argv);

	if (optind != argc-1) 
	{
		usage();
		exit(1);
	}
	
	host = argv[optind];
	addr = host_serv(host);
	
	pid = getpid();
	Signal(SIGALRM, sig_alrm);
	
	printf("traceroute to %s (%s): %d hops max, %d data bytes\n",
		   host,
		   inet_ntoa(addr->sin_addr),
		   max_ttl, 
		   datalen);


	pr = &proto_v4;	
	pr->sasend = (struct sockaddr *)addr;		/* contains destination address */
	pr->sarecv = Calloc(1, sizeof (struct sockaddr));
	pr->salast = Calloc(1, sizeof (struct sockaddr));
	pr->sabind = Calloc(1, sizeof (struct sockaddr));
	pr->salen  = sizeof (struct sockaddr);

	traceloop();

	return 0;
}
/****************************************************************/
Bind				traceroute.c	/^void Bind(int fd, const struct sockaddr *sa, sockl/
Calloc				traceroute.c	/^void * Calloc(size_t n, size_t size)$/
Gethostbyaddr		traceroute.c	/^struct hostent *Gethostbyaddr(const char *name, in/
Gethostbyname		traceroute.c	/^struct hostent *Gethostbyname(const char *name)$/
Gettimeofday		traceroute.c	/^void Gettimeofday(struct timeval *tv, void *foo)$/
Inet_ntop			traceroute.c	/^char* Inet_ntop(const struct sockaddr *sa, socklen/
Mtraceroute			traceroute.c	/^int main(int argc, char* argv[])$/
Sendto				traceroute.c	/^void Sendto(int fd, const void *ptr, size_t nbytes/
Setsockopt			traceroute.c	/^void Setsockopt(int fd, int level, int optname, co/
Signal				traceroute.c	/^Sigfunc * Signal(int signo, Sigfunc *func)        /
Socket				traceroute.c	/^int Socket(int family, int type, int protocol)$/
create_recv_socket	traceroute.c	/^int create_recv_socket()$/
create_send_socket	traceroute.c	/^int create_send_socket()$/
host_serv			traceroute.c	/^struct sockaddr_in* host_serv(const char *host)$/
icmpcode			traceroute.c	/^char * icmpcode(int code)$/
print_status		traceroute.c	/^void print_status(int code, struct timeval *tvrecv/
recv_icmp_data		traceroute.c	/^int recv_icmp_data(int seq, struct timeval *tv)$/
send_udp_data		traceroute.c	/^void send_udp_data(seq)$/
sig_alrm			traceroute.c	/^void sig_alrm(int signo)$/
sock_cmp_addr		traceroute.c	/^int sock_cmp_addr(const struct sockaddr *sa1, cons/
traceloop			traceroute.c	/^void traceloop()$/
tv_sub				traceroute.c	/^void tv_sub(struct timeval *out, struct timeval *i/
usage				traceroute.c	/^int usage()$/
validate_args		traceroute.c	/^void validate_args(int argc, char **argv)$/
/****************************************************************/
