/*##########################################################################################*/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Schema.
	/// </summary>
/*##########################################################################################*/
	[Serializable]
	public class Schema
	{
/********************************************************************************/
		public string schema_name;
		public Hashtable functions;
		public Hashtable procedures;
		public Hashtable tables;
		public Hashtable views;
		public Hashtable sequences;
		public Hashtable users;
		public Hashtable dblinks;
		public Hashtable triggers;
/********************************************************************************/
		public Schema(string schema_name)
		{
			this.schema_name = schema_name;

			functions = new Hashtable();
			procedures = new Hashtable();
			tables = new Hashtable();
			views = new Hashtable();
			sequences = new Hashtable();
			users = new Hashtable();
			dblinks = new Hashtable();
			triggers = new Hashtable();
//			init_functions();
//			init_procedures();
//			init_triggers();
//			init_sequences();
//			init_dblinks();
//			init_tables();
//			init_views();
//			init_users();
		}
/********************************************************************************/
		private void init_functions()
		{
			functions = new Hashtable();
			Function f;
			if (schema_name.Equals("school_schema"))
			{
				f = new Function("getStudentMarks", "user1", DateTime.Now,
					"create or replace function getStudentMarks(student_id as number) \n " + 
					"return 0; \n" + 
					"end getStudentMarks;");
			}
			else
			{
				f = new Function("getSchedule", "user1", DateTime.Now,
					"create or replace function getSchedule(course_id as number) \n " + 
					"return 0; \n" + 
					"end getSchedule;");
			}
			addFunction(f);
		}
/********************************************************************************/
//		public Hashtable getFunctions()
//		{
//			return functions;
//		}
/********************************************************************************/
		public bool addFunction(Function f)
		{
			functions.Add(f.function_name, f);
			return true;
		}
/********************************************************************************/
		public bool addFunction(string function_name, string created_by, 
								DateTime created_on, string function_sql)
		{
			Function f = new Function(function_name, created_by, created_on, function_sql);
			functions.Add(f.function_name, f);
			return true;
		}
/********************************************************************************/
		public bool updateFunction(string function_name, string function_sql)
		{
			Function f = getFunction(function_name);
			if (f == null)
			{
				f = new Function(function_name, schema_name, DateTime.Now, function_sql);
			}
			else
			{
				f.function_sql = function_sql;
			}
			functions.Remove(f.function_name);
			functions.Add(f.function_name, f);
			return true;
		}
/********************************************************************************/
		public bool deleteFunction(string function_name)
		{
			functions.Remove(function_name);
			return true;
		}
/********************************************************************************/
		public Function getFunction(string function_name)
		{
			return (Function)functions[function_name];
		}
/********************************************************************************/
		private void init_procedures()
		{
			procedures = new Hashtable();
			Procedure p;
			if (schema_name.Equals("school_schema"))
			{
				p = new Procedure("displayStudentMarks", "user1", DateTime.Now,
					"create or replace procedure getStudentMarks(student_id as number) \n " + 
					"DBMS.Prinln('0'); \n" + 
					"end displayStudentMarks;");
			}
			else
			{
				p = new Procedure("conduct_demo", "user1", DateTime.Now,
					"create or replace procedure conduct_demo(student_id as number) \n " + 
					"DBMS.Prinln(student_id || 'gets FULL MARKS. :-)'); \n" + 
					"end conduct_demo;");
			}
			addProcedure(p);
		}
/********************************************************************************/
//		public Hashtable getProcedures()
//		{
//			return procedures;
//		}
/********************************************************************************/
		public bool addProcedure(Procedure p)
		{
			procedures.Add(p.procedure_name, p);
			return true;
		}
/********************************************************************************/
		public bool addProcedure(string procedure_name, string created_by, 
									DateTime created_on, string procedure_sql)
		{
			Procedure p = new Procedure(procedure_name, created_by, created_on, procedure_sql);
			procedures.Add(p.procedure_name, p);
			return true;
		}
/********************************************************************************/
		public bool updateProcedure(string procedure_name, string procedure_sql)
		{
			Procedure p = getProcedure(procedure_name);
			if (p == null)
			{
				p = new Procedure(procedure_name, schema_name, DateTime.Now, procedure_sql);
			}
			else
			{
				p.procedure_sql = procedure_sql;
			}
			procedures.Remove(p.procedure_name);
			procedures.Add(p.procedure_name, p);
			return true;
		}
/********************************************************************************/
		public bool deleteProcedure(string procedure_name)
		{
			procedures.Remove(procedure_name);
			return true;
		}
/********************************************************************************/
		public bool deleteView(string view_name)
		{
			views.Remove(view_name);
			return true;
		}
/********************************************************************************/
		public Procedure getProcedure(string procedure_name)
		{
			return (Procedure)procedures[procedure_name];
		}
/********************************************************************************/
		private void init_triggers()
		{
			triggers = new Hashtable();
			Trigger t;
			if (schema_name.Equals("school_schema"))
			{
				t = new Trigger("student", "before", "insert", "updateStudentID", "user1", DateTime.Now,
					"create or replace trigger updateStudentID() \n " + 
					"new.stud_id = seq_stud_id.nextval; \n" + 
					"end updateStudentID;");
			}
			else
			{
				t = new Trigger("faculty", "before", "insert", "updateFacultyID", "user1", DateTime.Now,
					"create or replace trigger updateFacultyID() \n " + 
					"new.fac_id = seq_fac_id.nextval; \n" + 
					"end updateFacultyID;");
			}
			addTrigger(t);
		}
/********************************************************************************/
//		public Hashtable getTriggers()
//		{
//			return triggers;
//		}
/********************************************************************************/
		public bool addTrigger(Trigger t)
		{
			triggers.Add(t.trigger_name, t);
			return true;
		}
/********************************************************************************/
		public bool addTrigger(string table_name, string fires, string fires_on,
								string trigger_name, string created_by, 
								DateTime created_on, string trigger_sql)
		{
			Trigger t = new Trigger(table_name, fires, fires_on, trigger_name, 
									created_by, created_on, trigger_sql);
			triggers.Add(t.trigger_name, t);
			return true;
		}
/********************************************************************************/
		public bool updateTrigger(string trigger_name, string trigger_sql)
		{
			Trigger t = getTrigger(trigger_name);
			if (t == null)
			{
				t = new Trigger("", "", "", trigger_name, schema_name, DateTime.Now, trigger_sql);
			}
			else
			{
				t.trigger_sql = trigger_sql;
			}
			triggers.Remove(t.trigger_name);
			triggers.Add(t.trigger_name, t);
			return true;
		}
/********************************************************************************/
		public bool deleteTrigger(string trigger_name)
		{
			triggers.Remove(trigger_name);
			return true;
		}
/********************************************************************************/
		public Trigger getTrigger(string trigger_name)
		{
			return (Trigger)triggers[trigger_name];
		}
/********************************************************************************/
		private void init_sequences()
		{
			sequences = new Hashtable();
			Sequence s;
			if (schema_name.Equals("school_schema"))
			{
				s = new Sequence("seq_stud_id", "user1", DateTime.Now, "1", "99999999", "1", "1", "0", false, true);
			}
			else
			{
				s = new Sequence("seq_fac_id", "user1", DateTime.Now, "1", "99999999", "1", "1", "0", false, true);
			}
			addSequence(s);
		}
/********************************************************************************/
//		public Hashtable getTriggers()
//		{
//			return triggers;
//		}
/********************************************************************************/
		public bool addSequence(Sequence s)
		{
			sequences.Add(s.sequence_name, s);
			return true;
		}
/********************************************************************************/
		public bool addSequence(string sequence_name, string created_by, DateTime created_on,
								string min_value, string max_value, string start_with, string increment_by,
								string cache, bool cycle, bool order)
		{
			Sequence s = getSequence(sequence_name);
			if (s == null)
			{
				s = new Sequence(sequence_name, created_by, created_on, min_value, max_value, start_with,increment_by, cache,cycle,order);
			}
			else
			{
				s = new Sequence(sequence_name, s.created_by, s.created_on, min_value, max_value, start_with,increment_by, cache,cycle,order);
			}
			sequences.Remove(s.sequence_name);
			sequences.Add(s.sequence_name, s);
			return true;
		}
/********************************************************************************/
		public bool updateSequence(string sequence_name, string min_value, string max_value, 
									string start_with, string increment_by, string cache, bool cycle, bool order)
		{
			Sequence s = getSequence(sequence_name);
			if (s == null)
			{
				s = new Sequence(sequence_name, schema_name, DateTime.Now, min_value, max_value, 
									start_with, increment_by, cache, cycle, order);
			}
			else
			{
				s = new Sequence(sequence_name, s.created_by, s.created_on, min_value, max_value, 
									start_with, increment_by, cache, cycle, order);
			}
			sequences.Remove(s.sequence_name);
			sequences.Add(s.sequence_name, s);
			return true;
		}
/********************************************************************************/
		public bool deleteSequence(string sequence_name)
		{
			sequences.Remove(sequence_name);
			return true;
		}
/********************************************************************************/
		public Sequence getSequence(string sequence_name)
		{
			return (Sequence)sequences[sequence_name];
		}
/********************************************************************************/
		private void init_dblinks()
		{
			dblinks = new Hashtable();
			DBLink d;
			if (schema_name.Equals("school_schema"))
			{
				d = new DBLink("dblink1", schema_name, DateTime.Now, "intapps", "intapps", "intapps.patni.com", "", "");
				addDBLink(d);
			}
		}
/********************************************************************************/
//		public Hashtable getDBLinks()
//		{
//			return dblinks;
//		}
/********************************************************************************/
		public bool addDBLink(DBLink d)
		{
			dblinks.Add(d.dblink_name, d);
			return true;
		}
/********************************************************************************/
		public bool addDBLink(string dblink_name, string created_by, DateTime created_on, 
								string uname, string passwd, string database, 
								string authby_uname, string authby_passwd)
		{
			DBLink d = new DBLink(dblink_name, created_by, created_on, uname, passwd, database, 
									authby_uname, authby_passwd);
			dblinks.Add(d.dblink_name, d);
			return true;
		}
/********************************************************************************/
		public bool updateDBLink(string dblink_name, string uname, string passwd, string database, 
									string authby_uname, string authby_passwd)
		{
			DBLink d = getDBLink(dblink_name);
			if (d == null)
			{
				d = new DBLink(dblink_name, schema_name, DateTime.Now, uname, passwd, database, 
								authby_uname, authby_passwd);
			}
			else
			{
				d = new DBLink(dblink_name, d.created_by, d.created_on, uname, passwd, database, 
								authby_uname, authby_passwd);
			}
			dblinks.Remove(d.dblink_name);
			dblinks.Add(d.dblink_name, d);
			return true;
		}
/********************************************************************************/
		public bool deleteDBLink(string dblink_name)
		{
			dblinks.Remove(dblink_name);
			return true;
		}
/********************************************************************************/
		public bool updateView(string view_name, string view_sql)
		{
			View v = getView(view_name);
			if (v == null)
			{
				v = new View(view_name, "", DateTime.Now, view_sql);
			}
			else
			{
				v.view_sql = view_sql;
			}
			views.Remove(v.view_name);
			views.Add(v.view_name, v);
			return true;
		}
/********************************************************************************/
		public DBLink getDBLink(string dblink_name)
		{
			return (DBLink)dblinks[dblink_name];
		}
/********************************************************************************/
		public Table getTable(string table_name)
		{
			return (Table)tables[table_name];
		}
/********************************************************************************/
		public View getView(string view_name)
		{
			return (View)views[view_name];
		}
/********************************************************************************/
		public bool addView(View v)
		{
			views.Add(v.view_name, v);
			return true;
		}
/********************************************************************************/
		public bool addView(string view_name, string created_by, DateTime created_on, string view_sql)
		{
			View v = new View(view_name, created_by, created_on, view_sql);
			views.Add(v.view_name, v);
			return true;
		}
/********************************************************************************/
		private void init_tables()
		{
			tables = new Hashtable();
			Table t;
			if (schema_name.Equals("school_schema"))
			{
				t = new Table("student", schema_name, DateTime.Now);
				t.addColumn("stud_id", "number", 5, true, "", "Student ID");
				t.addColumn("stud_name", "varchar", 100, true, "", "Student Name");

				string[] d1 = {"10", "Yogesh R Naik"};
				string[] d2 = {"20", "Amar"};
				string[] d3 = {"30", "Mittal"};
				string[] d4 = {"40", "Ruchi"};
				t.addData(d1);
				t.addData(d2);
				t.addData(d3);
				t.addData(d4);
			}
			else
			{
				t = new Table("faculty", schema_name, DateTime.Now);
				t.addColumn("fac_id", "number", 5, true, "", "Faculty ID");
				t.addColumn("fac_name", "varchar", 100, true, "", "Faculty Name");

				string[] d1 = {"10", "Maria"};
				string[] d2 = {"20", "Shrinath"};
				string[] d3 = {"30", "Sashi"};
				string[] d4 = {"40", "KC Rao"};
				t.addData(d1);
				t.addData(d2);
				t.addData(d3);
				t.addData(d4);
			}
			tables.Add(t.table_name, t);
		}
/********************************************************************************/
		public bool updateTableDefinition(string table_name, DataTable tableDefinition)
		{
			Table t = getTable(table_name);
			t.table_columns = tableDefinition;
			return true;
		}
/********************************************************************************/
		//get the Tree representation of this schema
		//this function returns a Root Tree Node named "Database Objects"
		public TreeNode getTreeRepresentation() 
		{
			int folderImgIndex = MyImageList.getImgList().getImageIndex("FOLDER");
				
			TreeNode root = new TreeNode("Database Objects", folderImgIndex, folderImgIndex);
			TreeNode funcNode = new TreeNode("Functions (" + functions.Count + ")", 
												folderImgIndex, folderImgIndex);
			int imgIndex;
			/*-----------------------------------------------------------------*/
			TreeNode dblinkNode = new TreeNode("DB Links (" + dblinks.Count + ")", 
				folderImgIndex, folderImgIndex);
			foreach(DBLink d in dblinks.Values)
			{
				imgIndex = MyImageList.getImgList().getImageIndex("DBLINK");
				TreeNode tr = new TreeNode(d.dblink_name, imgIndex, imgIndex);
				tr.Tag = "DBLink";
				dblinkNode.Nodes.Add(tr);
			}
			root.Nodes.Add(dblinkNode);
			/*-----------------------------------------------------------------*/
			foreach(Function f in functions.Values)
			{
				if (f.isCompiled)
					imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_FUNCTION");
				else
					imgIndex = MyImageList.getImgList().getImageIndex("FUNCTION_WITH_ERRORS");
				TreeNode tr = new TreeNode(f.function_name, imgIndex, imgIndex);
				tr.Tag = "Function";
				funcNode.Nodes.Add(tr);
			}
			root.Nodes.Add(funcNode);
			/*-----------------------------------------------------------------*/
			TreeNode procNode = new TreeNode("Procedures (" + procedures.Count + ")", 
												folderImgIndex, folderImgIndex);
			foreach(Procedure p in procedures.Values)
			{
				if (p.isCompiled)
					imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_PROCEDURE");
				else
					imgIndex = MyImageList.getImgList().getImageIndex("PROCEDURE_WITH_ERRORS");
				TreeNode tr = new TreeNode(p.procedure_name, imgIndex, imgIndex);
				tr.Tag = "Procedure";
				procNode.Nodes.Add(tr);
			}
			root.Nodes.Add(procNode);
			/*-----------------------------------------------------------------*/
			TreeNode seqNode = new TreeNode("Sequences (" + sequences.Count + ")", 
												folderImgIndex, folderImgIndex);
			foreach(Sequence s in sequences.Values)
			{
				imgIndex = MyImageList.getImgList().getImageIndex("SEQUENCE");
				TreeNode tr = new TreeNode(s.sequence_name, imgIndex, imgIndex);
				tr.Tag = "Sequence";
				seqNode.Nodes.Add(tr);
			}
			root.Nodes.Add(seqNode);
			/*-----------------------------------------------------------------*/
			TreeNode tableNode = new TreeNode("Tables (" + tables.Count + ")", 
													folderImgIndex, folderImgIndex);
			foreach(Table t in tables.Values)
			{
				imgIndex = MyImageList.getImgList().getImageIndex("TABLE");
				TreeNode tr = new TreeNode(t.table_name, imgIndex, imgIndex);
				tr.Tag = "Table";
				tableNode.Nodes.Add(tr);
			}
			root.Nodes.Add(tableNode);
			/*-----------------------------------------------------------------*/
			TreeNode triggerNode = new TreeNode("Triggers (" + triggers.Count + ")", 
				folderImgIndex, folderImgIndex);
			foreach(Trigger t in triggers.Values)
			{
				if (t.isCompiled)
					imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_TRIGGER");
				else
					imgIndex = MyImageList.getImgList().getImageIndex("TRIGGER_WITH_ERRORS");

				TreeNode tr = new TreeNode(t.trigger_name, imgIndex, imgIndex);
				tr.Tag = "Trigger";
				triggerNode.Nodes.Add(tr);
			}
			root.Nodes.Add(triggerNode);
			/*-----------------------------------------------------------------*/
			TreeNode userNode = new TreeNode("Users (" + users.Count + ")", 
												folderImgIndex, folderImgIndex);
			foreach(User u in users.Values)
			{
				imgIndex = MyImageList.getImgList().getImageIndex("USER");

				TreeNode tr = new TreeNode(u.username, imgIndex, imgIndex);
				tr.Tag = "User";
				userNode.Nodes.Add(tr);
			}
			root.Nodes.Add(userNode);
			/*-----------------------------------------------------------------*/
			TreeNode viewNode = new TreeNode("Views (" + views.Count + ")", 
												folderImgIndex, folderImgIndex);
			foreach(View v in views.Values)
			{
				imgIndex = MyImageList.getImgList().getImageIndex("VIEW");

				TreeNode tr = new TreeNode(v.view_name, imgIndex, imgIndex);
				tr.Tag = "View";
				viewNode.Nodes.Add(tr);
			}
			root.Nodes.Add(viewNode);
			/*-----------------------------------------------------------------*/
			
			return root;
			/*-----------------------------------------------------------------*/
		}
/********************************************************************************/
		//Mittal Start
		public bool addTable(Table t)
		{
			tables.Add(t.table_name, t);
			return true;
		}
/********************************************************************************/
		public bool addTable(string table_name, string created_by, 
			DateTime created_on)
		{
			Table t = new Table(table_name, created_by, created_on);
			tables.Add(t.table_name, t);
			return true;
		}
/********************************************************************************/
		public bool updateTable(string table_name, DataTable dt_columns, DataTable dt_keys, DataTable dt_checks, DataTable dt_indexes, DataTable dt_data, DataTable dt_priviledges)
		{
			Table t = getTable(table_name);
			if (t == null)
			{
				t = new Table(table_name, schema_name, DateTime.Now);
			}
			else
			{
				t.table_columns = dt_columns;
				t.keys = dt_keys;
				t.indexes = dt_indexes;
				t.checks = dt_checks;
				t.priviledges = dt_priviledges;
				t.table_data = dt_data;
			}
			tables.Remove(t.table_name);
			tables.Add(t.table_name, t);
			return true;
		}
/********************************************************************************/
		public bool deleteTable(string table_name)
		{
			tables.Remove(table_name);
			return true;
		}
/********************************************************************************/
		
		//Mittal End
	}	//end of Schema Class 
/*##########################################################################################*/
}
/*##########################################################################################*/