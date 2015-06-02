;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun getPath (start end)
	(setq graph '((a (b 5) (c 4)) (b (a 5) (d 3)) (c (a 4) (d 7) (f 2)) (d (b 3) (c 7) (e 7)) (e (d 7)) (f (c 2))))
	(setf nbrs (get-neighbors graph start))
	(mypath (first (first nbrs)) end (list start) 0 (second (first nbrs)))
	(format t  "Path = ")
	(princ finalpath)
	(format t "~%Total Time = ")
	(princ totaltime))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun mypath (currnode end sofarpath sofartime currtime)
	(setf sofarpath (append sofarpath (list currnode)))	;;visit the node
	(setf sofartime (+ sofartime currtime))
	(if (equal currnode end)				;;check if path complete
	    (setpath sofarpath sofartime)			;;return final path
	    (path-via-nbrs currnode end sofarpath sofartime)))	;;do for all nbrs of visited node
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun setpath (path time)
	(setq finalpath path)
	(setq totaltime time))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun path-via-nbrs (currnode end sofarpath sofartime)
	(setf nbrs (get-neighbors graph currnode))
	(dolist (next nbrs sofarpath)
		(if (equal (not-member (first next) sofarpath) 'true)
		    (mypath (first next) end sofarpath sofartime (second next)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; get a list of all neighbors of an node of the graph
;; this function takes two parameters - first is the graph in the form of a list and second is node whose
;; neighbors are to be found
(defun get-neighbors (glist node)
	(if 	(equal (first (first glist)) node)
		(rest (first glist))
		(get-neighbors (rest glist) node)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun is-member (elem lis)
	(if (equal lis ())
		(setf result 'false)
		(if (equal (first lis) elem)
	    		(setf result 'true)
			(is-member elem (rest lis)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun not-member (elem lis)
	(if (equal lis ())
		(setf result 'true)
		(if (equal (first lis) elem)
	    		(setf result 'false)
			(not-member elem (rest lis)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

