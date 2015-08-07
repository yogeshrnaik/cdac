/********************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Database.
	/// </summary>
	[Serializable]
	public class Database
	{
/********************************************************************************/
		public string database_name;
		public Hashtable schemas;	//contains all Schema objects
/********************************************************************************/
		public Database(string database_name)
		{
			this.database_name = database_name;	
			schemas = new Hashtable();

//			string schema_name = "school_schema";
//			Schema schema = new Schema(schema_name);
//			schemas.Add(schema_name, schema);
//
//			schema_name = "ncst_schema";
//			schema = new Schema(schema_name);
//			schemas.Add(schema_name, schema);
		}
/********************************************************************************/
		public Schema getSchema(string schema_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			return sch;
		}
/********************************************************************************/
		public void addSchema(string schema_name)
		{
			Schema sch = new Schema(schema_name);
			schemas.Add(schema_name, sch);
		}
/********************************************************************************/
		public Function getFunction(string schema_name, string function_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getFunction(function_name);
			}
			return null;
		}
/********************************************************************************/
		public Procedure getProcedure(string schema_name, string procedure_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getProcedure(procedure_name);
			}
			return null;
		}
/********************************************************************************/
		public Trigger getTrigger(string schema_name, string trigger_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getTrigger(trigger_name);
			}
			return null;
		}
/********************************************************************************/
		public View getView(string schema_name, string view_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getView(view_name);
			}
			return null;
		}
/********************************************************************************/
		public Sequence getSequence(string schema_name, string sequence_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getSequence(sequence_name);
			}
			return null;
		}
/********************************************************************************/
		public DBLink getDBLink(string schema_name, string dblink_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getDBLink(dblink_name);
			}
			return null;
		}
/********************************************************************************/
		public Table getTable(string schema_name, string table_name)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.getTable(table_name);
			}
			return null;
		}
/********************************************************************************/
		public bool updateFunction(string schema_name, string function_name, string function_sql)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateFunction(function_name, function_sql);
			}
			return false;
		}
/********************************************************************************/
		public bool updateProcedure(string schema_name, string procedure_name, string procedure_sql)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateProcedure(procedure_name, procedure_sql);
			}
			return false;
		}
/********************************************************************************/
		public bool updateSequence(string schema_name, string sequence_name, string min_value, string max_value, 
										string start_with, string increment_by, string cache, bool cycle, bool order)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateSequence(sequence_name, min_value, max_value, start_with, increment_by, cache, cycle, order);
			}
			return false;
		}
/********************************************************************************/
		public bool updateTrigger(string schema_name, string trigger_name, string trigger_sql)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateTrigger(trigger_name, trigger_sql);
			}
			return false;
		}
/********************************************************************************/
		public bool updateView(string schema_name, string view_name, string view_sql)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateView(view_name, view_sql);
			}
			return false;
		}
/********************************************************************************/
		//Mittal - changed function defination.
		public bool updateTable(string schema_name, string table_name, DataTable table_columns,DataTable dt_keys, DataTable dt_checks, DataTable dt_indexes, DataTable dt_data, DataTable dt_priviledges)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateTable(table_name, table_columns,dt_keys, dt_checks, dt_indexes, dt_data,dt_priviledges);
			}
			return false;
		}
		public bool updateTable(string schema_name, string table_name, DataTable table_columns)
		{
			Schema sch = (Schema)schemas[schema_name];
			if (sch != null)
			{
				return sch.updateTableDefinition(table_name, table_columns);
			}
			return false;
		}
/********************************************************************************/
		//get the Tree representation of the database schema
		//this function returns a Root Tree Node of a Tree View
		public TreeNode getTreeViewOfSchema(string schema_name)
		{
			//schema_name.ToUpper().Equals("ALL")
			Schema sch;
			if (schema_name.Length == 0)
			{
				if (schemas.Count > 0)
				{
					IDictionaryEnumerator ie = schemas.GetEnumerator();
					ie.MoveNext();
					sch = (Schema)schemas[ie.Key];
				}
				else
				{
					return getEmptySchema();
				}
			}
			else
			{
				sch = (Schema)schemas[schema_name];
			}
			if (sch != null)
			{
				return sch.getTreeRepresentation();
			}
			TreeNode root = getEmptySchema();
			return root;
		}
/********************************************************************************/
		private TreeNode getEmptySchema()
		{
			TreeNode root = new TreeNode("Database Objects", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER"), 
				new TreeNode[] 
						{
							new TreeNode("DB Links(0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Functions (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Procedures (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Sequences (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Tables (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Triggers (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Users (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Views (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER"))
						});
			return root;
		}
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
	}
/*##########################################################################################*/
}
