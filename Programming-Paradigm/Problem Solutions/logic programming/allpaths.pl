%edge(Node1, Node2, Time_required)
edge(a, b, 5).
edge(a, c, 4).
edge(b, d, 3).
edge(c, d, 7).
edge(d, e, 7).

%path(start, end, Path, Time)
path(Start, Stop, Path, Time) :-  
			path1(Start, Stop, [Start], Path, Time).

path1(Stop, Stop, Path, Path, 0).
path1(Start, Stop, CurrPath, Path, Time):-
                Start\=Stop,
                getEdge(Start, Next, T1),
                non_member(Next, CurrPath),
                append(CurrPath, [Next], NewPath),
                path1(Next, Stop, NewPath, Path, T2),
                Time is T1+T2.

non_member(_,[]).
non_member(X,[Y|T]):-    X\=Y, non_member(X,T).

append([], T, T).
append([H|T], O, [H|New]) :- append(T, O, New).

getEdge(Start, Next, Time) :- edge(Start, Next, Time).
getEdge(Start, Next, Time) :- edge(Next, Start, Time).
