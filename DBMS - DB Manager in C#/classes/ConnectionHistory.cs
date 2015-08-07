/********************************************************************************/
using System;
using System.Data;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for ConnectionHistory.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class ConnectionHistory
	{
/********************************************************************************/
		public DataTable connHistTable;
		public bool saveAllPwds;
		public bool autoConnectAll;
/********************************************************************************/
		public ConnectionHistory()
		{
			createConHist();
			saveAllPwds = false;
			autoConnectAll = false;
		}
/********************************************************************************/
		// Create a dummay connection history
		private void createConHist()
		{
			connHistTable = new DataTable("ConnectionHistory");
			/*---------------------------------------------------------*/
			// Create columns 
			addConHistoryColumn("User", "System.String", "User", true, false);
			addConHistoryColumn("Database", "System.String", "Database", true, false);
			addConHistoryColumn("Last_Connected", "System.String", "Last Connected", true, false);
			addConHistoryColumn("Save_passwd", "System.Boolean", "Save Pwd", false, false);
			addConHistoryColumn("Auto_conn", "System.Boolean", "Auto Connect", false, false);
			addConHistoryColumn("Password", "System.String", "Password", false, false);
			/*---------------------------------------------------------*/
			// Create three rows to the table
//			addConHistoryRow("user1", "database1", DateTime.Now.AddDays(-5),false, true, "");
//			addConHistoryRow("user2", "database2", DateTime.Now.AddDays(-3), false, false, "");
//			addConHistoryRow("user3", "database3", DateTime.Now.AddDays(-1), false, true, "");
		}
/***************************************************************************/
		public void addConHistoryColumn(string col_name, string datatype, string caption, 
										bool isReadonly, bool isUnique)
		{
			DataColumn dtCol;
			dtCol = new DataColumn();
			dtCol.ColumnName = col_name;
			dtCol.DataType= System.Type.GetType(datatype);
			//dtCol.AutoIncrement = false;
			dtCol.Caption = caption;
			dtCol.ReadOnly = isReadonly;
			dtCol.Unique = isUnique;
			connHistTable.Columns.Add(dtCol);
		}
/***************************************************************************/
		public void addConHistoryRow(string user, string database, DateTime last_connected, 
										bool save_passwd, bool auto_connect, string passwd)
		{
			//check whether the row already exists. if yes, 
			//then update last_connected, save password and auto connect columns only
			for (int i=0; i < connHistTable.Rows.Count; i++)
			{
				if (connHistTable.Rows[i]["User"].ToString().ToUpper().Equals(user.ToUpper()) && 
					connHistTable.Rows[i]["Database"].ToString().ToUpper().Equals(database.ToUpper()))
				{
					//change the last connected column
					connHistTable.Columns["Last_Connected"].ReadOnly = false;
					connHistTable.Rows[i]["Last_Connected"] = last_connected.ToString();
					connHistTable.Columns["Last_Connected"].ReadOnly = true;


					connHistTable.Columns["Save_passwd"].ReadOnly = false;
					connHistTable.Rows[i]["Save_passwd"] = save_passwd;
					connHistTable.Columns["Save_passwd"].ReadOnly = true;

					connHistTable.Columns["Auto_conn"].ReadOnly = false;
					connHistTable.Rows[i]["Auto_conn"] = auto_connect;
					connHistTable.Columns["Auto_conn"].ReadOnly = true;
					return;
				}
			}
			DataRow dtRow;
			dtRow = connHistTable.NewRow();
			dtRow["User"] = user;
			dtRow["Database"] = database;
			dtRow["Last_Connected"] = last_connected.ToString();
			dtRow["Save_passwd"] = save_passwd;
			dtRow["Auto_conn"] = auto_connect;
			if (save_passwd)
				dtRow["Password"] = passwd;
			else
				dtRow["Password"] = "";

			connHistTable.Rows.Add(dtRow);
		}
/***************************************************************************/
		public DataGridTableStyle getTableStyle()
		{
			DataGridTableStyle tableStyle = new DataGridTableStyle();
			tableStyle = new DataGridTableStyle() ;
			tableStyle.MappingName = "ConnectionHistory";

			DataGridTextBoxColumn column = new DataGridTextBoxColumn() ;
			column.MappingName = "User" ;
			column.HeaderText = "User";
			column.Width = 80 ;
			tableStyle.GridColumnStyles.Add ( column ) ;
 
			DataGridTextBoxColumn column2 = new DataGridTextBoxColumn() ;
			column2.MappingName = "Database" ;
			column2.HeaderText = "Database";
			column2.Width = 80 ;
			tableStyle.GridColumnStyles.Add (column2) ;

			DataGridTextBoxColumn column3 = new DataGridTextBoxColumn() ;
			column3.MappingName = "Last_Connected" ;
			column3.HeaderText = "Last Connected";
			column3.Width = 150;
			tableStyle.GridColumnStyles.Add (column3) ;

			DataGridBoolColumn bcol1 = new DataGridBoolColumn() ;
			bcol1.MappingName = "Save_passwd";
			bcol1.HeaderText = "Save Password";
			bcol1.Width = 100 ;
			bcol1.AllowNull = false ;
			tableStyle.GridColumnStyles.Add (bcol1) ;
			
 			DataGridBoolColumn bcol2 = new DataGridBoolColumn() ;
			bcol2.MappingName = "Auto_Conn";
			bcol2.HeaderText = "Auto Connect";
			bcol2.Width = 100 ;
			bcol2.AllowNull = false ;
			tableStyle.GridColumnStyles.Add (bcol2) ;

//			DataGridTextBoxColumn column4 = new DataGridTextBoxColumn() ;
//			column3.MappingName = "Password" ;
//			column3.HeaderText = "Password";
//			column3.Width = 50;
//			tableStyle.GridColumnStyles.Add (column4) ;

			
			return tableStyle;
		}
	}
/********************************************************************************/
}
/********************************************************************************/