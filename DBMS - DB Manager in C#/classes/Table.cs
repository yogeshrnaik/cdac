/********************************************************************************/
using System;
using System.Data;
using System.Windows.Forms;
//Mittal Start
using System.Drawing;
using System.Collections;
using System.ComponentModel;
//Mittal End

/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Table.
	/// </summary>
	/********************************************************************************/
	[Serializable]
	public class Table
	{
		public string table_name;
		public string created_by;
		public DateTime created_on;

		public DataTable table_data;
		public DataTable table_columns;
		public DataTable keys;
		public DataTable checks;
		public DataTable indexes;
		public DataTable priviledges;

		public string table_sql;
		/********************************************************************************/
		//for new table
		public Table() 
		{
			this.table_name = "";
			this.created_by = "";
			//this.created_on = "";
			table_data = new DataTable();
			table_columns = new DataTable("table_columns");
			/*table_columns.Columns.Add("column_name");
			table_columns.Columns.Add("datatype");
			table_columns.Columns.Add("size");
			table_columns.Columns.Add("nullable");
			table_columns.Columns.Add("default_value");
			table_columns.Columns.Add("comments");*/
			addtableColumn("column_name", "System.String", "Column Name", false, false);
			addtableColumn("datatype", "System.String", "Datatype", false, false);
			addtableColumn("size", "System.Int32", "Size", false, false);
			addtableColumn("nullable", "System.Boolean", "Nullable", false, false);
			addtableColumn("default_value", "System.String", "Default Value", false, false);
			addtableColumn("comments", "System.String", "Comments", false, false);
			
			keys = new DataTable("table_keys");
			addkeyColumn("key_name","System.String","Key Name",false,false);
			addkeyColumn("type","System.String","Key Type",false,false);
			addkeyColumn("columns","System.String","Columns",false,false);
			addkeyColumn("enabled","System.Boolean","Enabled",false,false);
			addkeyColumn("ref_tables","System.String","Ref Tables",false,false);
			addkeyColumn("ref_columns","System.String","Ref Columns",false,false);

			checks = new DataTable("table_checks");
			addcheckColumn("check_name","System.String","Check Name",false,false);
			addcheckColumn("condition","System.String","condition",false,false);
			addcheckColumn("enabled","System.Boolean","Enabled",false,false);
			addcheckColumn("defered","System.Boolean","Deffered",false,false);
			addcheckColumn("last_changed","System.String","Last Change",false,false);
			
			indexes = new DataTable("table_indexes");
			addindexColumn("owner_name","System.String","Owner",false,false);
			addindexColumn("index_name","System.String","Index Name",false,false);
			addindexColumn("type","System.String","Type",false,false);
			addindexColumn("column","System.String","Columns",false,false);
			addindexColumn("storage","System.String","Storage",false,false);

			priviledges = new DataTable();

			//this.table_sql = this.createTableSQL();
		}
		/***************************************************************************/
		public Table(string table_name, string created_by, DateTime created_on) 
		{
			this.table_name = table_name;
			this.created_by = created_by;
			this.created_on = created_on;

			table_data = new DataTable();

			table_columns = new DataTable("table_columns");
			/*table_columns.Columns.Add("column_name");
			table_columns.Columns.Add("datatype");
			table_columns.Columns.Add("size");
			table_columns.Columns.Add("nullable");
			table_columns.Columns.Add("default_value");
			table_columns.Columns.Add("comments");*/
			addtableColumn("column_name", "System.String", "Column Name", false, false);
			addtableColumn("datatype", "System.String", "Datatype", false, false);
			addtableColumn("size", "System.Int32", "Size", false, false);
			addtableColumn("nullable", "System.Boolean", "Nullable", false, false);
			addtableColumn("default_value", "System.String", "Default Value", false, false);
			addtableColumn("comments", "System.String", "Comments", false, false);
			
			keys = new DataTable("table_keys");
			addkeyColumn("key_name","System.String","Key Name",false,false);
			addkeyColumn("type","System.String","Key Type",false,false);
			addkeyColumn("columns","System.String","Columns",false,false);
			addkeyColumn("enabled","System.Boolean","Enabled",false,false);
			addkeyColumn("ref_tables","System.String","Ref Tables",false,false);
			addkeyColumn("ref_columns","System.String","Ref Columns",false,false);

			checks = new DataTable("table_checks");
			addcheckColumn("check_name","System.String","Check Name",false,false);
			addcheckColumn("condition","System.String","condition",false,false);
			addcheckColumn("enabled","System.Boolean","Enabled",false,false);
			addcheckColumn("defered","System.Boolean","Deffered",false,false);
			addcheckColumn("last_changed","System.String","Last Change",false,false);
			
			indexes = new DataTable("table_indexes");
			addindexColumn("owner_name","System.String","Owner",false,false);
			addindexColumn("index_name","System.String","Index Name",false,false);
			addindexColumn("type","System.String","Type",false,false);
			addindexColumn("column","System.String","Columns",false,false);
			addindexColumn("storage","System.String","Storage",false,false);

			priviledges = new DataTable();
			addpriviledgeColumn("grantee","System.String","Grantee",false,false);
			addpriviledgeColumn("select","System.String","Select",false,false);
			addpriviledgeColumn("insert","System.String","Insert",false,false);
			addpriviledgeColumn("update","System.String","Update",false,false);
			addpriviledgeColumn("delete","System.String","Delete",false,false);
			

			//adddataColumn(this.table_columns.Columns[1].ColumnName.ToString();

			//this.table_sql = table_sql;
		}
		//Mittal Start
		/***************************************************************************/
		public void addtableColumn(string col_name, string datatype, string caption, 
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
			table_columns.Columns.Add(dtCol);
		}
		public void addkeyColumn(string col_name, string datatype, string caption, 
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
			keys.Columns.Add(dtCol);
		}
		public void addcheckColumn(string col_name, string datatype, string caption, 
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
			checks.Columns.Add(dtCol);
		}
		public void addindexColumn(string col_name, string datatype, string caption, 
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
			indexes.Columns.Add(dtCol);
		}
		public void adddataColumn(string col_name, string datatype, string caption, 
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
			table_data.Columns.Add(dtCol);
		}
		public void addpriviledgeColumn(string col_name, string datatype, string caption, 
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
			priviledges.Columns.Add(dtCol);
		}
		/***************************************************************************/

		//Mittal End


		/********************************************************************************/
		public void addColumn(string col_name, string datatype, int size, bool nullable, 
			string default_value, string comments)
		{
			object[] s = {col_name, datatype, size, nullable, default_value, comments};
			table_columns.Rows.Add(s);
			//table_columns.Columns["column_name"].ReadOnly = true;
			table_data.Columns.Add(col_name);
			//set the default value for all rows
		}
		/********************************************************************************/
		public void addData(object[] data)
		{
			table_data.Rows.Add(data);
			//set the default value for all rows
		}

		//Mittal
		public DataGridTableStyle getTableStyleColumn()
		{
			DataGridTableStyle ts1 = new DataGridTableStyle();
			ts1.MappingName = "table_columns";
   
			DataGridTextBoxColumn column1 = new DataGridTextBoxColumn() ;
			column1.MappingName = "column_name" ;
			column1.HeaderText = "Column Name";
			column1.Width = 80 ;
			ts1.GridColumnStyles.Add ( column1 ) ;

			//DataGridTextBoxColumn column2 = new DataGridTextBoxColumn();
			DataGridComboBoxColumn column2 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged)); 
			column2.MappingName = "datatype";
			column2.HeaderText = "Datatype";
			column2.Width = 100 ;
			ts1.GridColumnStyles.Add ( column2 ) ;

			DataGridTextBoxColumn column3 = new DataGridTextBoxColumn() ;
			column3.MappingName = "size" ;
			column3.HeaderText = "Size";
			column3.Width = 80 ;
			ts1.GridColumnStyles.Add ( column3 ) ;
			
			DataGridBoolColumn bcol1 = new DataGridBoolColumn() ;
			bcol1.MappingName = "nullable";
			bcol1.HeaderText = "Nullable";
			bcol1.Width = 80 ;
			bcol1.AllowNull = false ;
			ts1.GridColumnStyles.Add (bcol1) ;
			
	
			DataGridTextBoxColumn column4 = new DataGridTextBoxColumn() ;
			column4.MappingName = "default_value" ;
			column4.HeaderText = "Default Value";
			column4.Width = 80 ;
			ts1.GridColumnStyles.Add ( column4 ) ;

			DataGridTextBoxColumn column5 = new DataGridTextBoxColumn() ;
			column5.MappingName = "comments" ;
			column5.HeaderText = "Comments";
			column5.Width = 80 ;
			ts1.GridColumnStyles.Add ( column5 ) ;
			//combo box
			
			// Step 3 - Additional setup for Combo style
			// a) make the row height a little larger to handle minimum combo height
			ts1.PreferredRowHeight = column2.ColumnComboBox.Height + 3;
			// b) Populate the combobox somehow. It is a normal combobox, so whatever...
			column2.ColumnComboBox.Items.Clear();
			column2.ColumnComboBox.Items.Add("Varchar2");
			column2.ColumnComboBox.Items.Add("Char");
			column2.ColumnComboBox.Items.Add("Number");
			column2.ColumnComboBox.Items.Add("Date");
			//column2.ColumnComboBox.Items.Add("");
			column2.ColumnComboBox.DropDownStyle = ComboBoxStyle.DropDownList;
			
			return ts1;
		}
		/********************************************************************************/
		public DataGridTableStyle getTableStyleKeys()
		{
			DataGridTableStyle ts1 = new DataGridTableStyle();
			ts1.MappingName = "table_columns";
			/*
			 * addkeyColumn("key_name","System.String","Key Name",false,false);
			addkeyColumn("type","System.String","Key Type",false,false);
			addkeyColumn("columns","System.String","Columns",false,false);
			addkeyColumn("enabled","System.Boolean","Enabled",false,false);
			addkeyColumn("ref_tables","System.String","Ref Tables",false,false);
			addkeyColumn("ref_columns","System.String","Ref Columns",false,false);
			 */
   
			DataGridTextBoxColumn column1 = new DataGridTextBoxColumn() ;
			column1.MappingName = "key_name" ;
			column1.HeaderText = "Key Name";
			column1.Width = 80 ;
			ts1.GridColumnStyles.Add ( column1 ) ;

			//DataGridTextBoxColumn column2 = new DataGridTextBoxColumn();
			DataGridComboBoxColumn column2 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged)); 
			column2.MappingName = "type";
			column2.HeaderText = "Type";
			column2.Width = 100 ;
			ts1.GridColumnStyles.Add ( column2 ) ;

			DataGridTextBoxColumn column3 = new DataGridTextBoxColumn() ;
			column3.MappingName = "columns" ;
			column3.HeaderText = "Columns";
			column3.Width = 80 ;
			ts1.GridColumnStyles.Add ( column3 ) ;
			
			DataGridBoolColumn bcol1 = new DataGridBoolColumn() ;
			bcol1.MappingName = "enabled";
			bcol1.HeaderText = "Enabled";
			bcol1.Width = 80 ;
			bcol1.AllowNull = false ;
			ts1.GridColumnStyles.Add (bcol1) ;
			
	
			DataGridTextBoxColumn column4 = new DataGridTextBoxColumn() ;
			column4.MappingName = "ref_tables" ;
			column4.HeaderText = "Ref Tables";
			column4.Width = 80 ;
			ts1.GridColumnStyles.Add ( column4 ) ;

			DataGridTextBoxColumn column5 = new DataGridTextBoxColumn() ;
			column5.MappingName = "ref_columns" ;
			column5.HeaderText = "Ref Columns";
			column5.Width = 80 ;
			ts1.GridColumnStyles.Add ( column5 ) ;
			//combo box
			
			// Step 3 - Additional setup for Combo style
			// a) make the row height a little larger to handle minimum combo height
			ts1.PreferredRowHeight = column2.ColumnComboBox.Height + 3;
			// b) Populate the combobox somehow. It is a normal combobox, so whatever...
			column2.ColumnComboBox.Items.Clear();
			column2.ColumnComboBox.Items.Add("Primary");
			column2.ColumnComboBox.Items.Add("Foreign");
			//column2.ColumnComboBox.Items.Add("");
			column2.ColumnComboBox.DropDownStyle = ComboBoxStyle.DropDownList;
			
			return ts1;
		}

		public void MyComboValueChanged(int rowChanging, object newValue)
		{
			Console.WriteLine("index changed {0} {1}", rowChanging, newValue);
		}
		
		/********************************************************************************/
	}
	/********************************************************************************/
	//Mittal Start
	
	public delegate void ComboValueChanged( int changingRow, object newValue );


	// Step 1. Derive a custom column style from DataGridTextBoxColumn
	//	a) add a ComboBox member
	//  b) track when the combobox has focus in Enter and Leave events
	//  c) override Edit to allow the ComboBox to replace the TextBox
	//  d) override Commit to save the changed data

	public class DataGridComboBoxColumn : DataGridTextBoxColumn
	{
		public NoKeyUpCombo ColumnComboBox = null;
		private System.Windows.Forms.CurrencyManager _source = null;
		private int _rowNum;
		private bool _isEditing = false;
		ComboValueChanged _valueChanging;
		
		public DataGridComboBoxColumn(ComboValueChanged valueChanging) : base()
		{
			_valueChanging = valueChanging;
			ColumnComboBox = new NoKeyUpCombo();
		
			ColumnComboBox.Leave += new EventHandler(LeaveComboBox);
			//			ColumnComboBox.Enter += new EventHandler(ComboMadeCurrent);
			ColumnComboBox.SelectedIndexChanged += new System.EventHandler(ComboIndexChanged);
			ColumnComboBox.SelectionChangeCommitted += new System.EventHandler(ComboStartEditing);
			
		}
		
		private void ComboStartEditing(object sender, EventArgs e)
		{
			_isEditing = true;
			base.ColumnStartedEditing((Control) sender);
		}
		
		private void ComboIndexChanged(object sender, EventArgs e)
		{
			_valueChanging(_rowNum , ColumnComboBox.Text); 	
		}

		//		private void ComboMadeCurrent(object sender, EventArgs e)
		//		{
		//			//_isEditing = true; 	
		//		}
		
		private void LeaveComboBox(object sender, EventArgs e)
		{
			if(_isEditing)
			{
				SetColumnValueAtRow(_source, _rowNum, ColumnComboBox.Text);
				_isEditing = false;
				Invalidate();
			}
			ColumnComboBox.Hide();
			
			
		}

		protected override void Edit(System.Windows.Forms.CurrencyManager source, int rowNum, System.Drawing.Rectangle bounds, bool readOnly, string instantText, bool cellIsVisible)
		{
	

			base.Edit(source,rowNum, bounds, readOnly, instantText , cellIsVisible);

			_rowNum = rowNum;
			_source = source;
		
			ColumnComboBox.Parent = this.TextBox.Parent;
			ColumnComboBox.Location = this.TextBox.Location;
			ColumnComboBox.Size = new Size(this.TextBox.Size.Width, ColumnComboBox.Size.Height);
			ColumnComboBox.SelectedIndexChanged -= new System.EventHandler(ComboIndexChanged);
			ColumnComboBox.Text =  this.TextBox.Text;
			ColumnComboBox.SelectedIndexChanged += new System.EventHandler(ComboIndexChanged);

			this.TextBox.Visible = false;
			ColumnComboBox.Visible = true;
			ColumnComboBox.BringToFront();
			ColumnComboBox.Focus();	
		}

		protected override bool Commit(System.Windows.Forms.CurrencyManager dataSource, int rowNum)
		{
			if(_isEditing)
			{
				_isEditing = false;
				SetColumnValueAtRow(dataSource, rowNum, ColumnComboBox.Text);
			}
			return true;
		}

	}
	//Mittal End
}
/********************************************************************************/