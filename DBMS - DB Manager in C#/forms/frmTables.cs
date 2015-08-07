using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace DBManager
{
	/// <summary>
	/// Summary description for frmTables.
	/// </summary>
	public class frmTables : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox textBox2;
		private System.Windows.Forms.Panel panTable;
		private System.Windows.Forms.Panel panTableUpper;
		private System.Windows.Forms.Panel panTableLower;
		private System.Windows.Forms.ToolBar tbTable;
		private System.Windows.Forms.TabControl tabTable;
		private System.Windows.Forms.TabPage tabpgColumn;
		private System.Windows.Forms.TabPage tabpgKey;
		private System.Windows.Forms.TabPage tabpgCheck;
		private System.Windows.Forms.TabPage tabpgIndex;
		private System.Windows.Forms.TabPage tabpgPriviledge;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.TextBox txtName;
		private System.Windows.Forms.TextBox txtOwner;
		private System.Windows.Forms.Button btnApply;
		private System.Windows.Forms.Button btnClose;
		private System.Windows.Forms.Button btnQuery;
		private System.Windows.Forms.Button btnViewSql;
		private System.Windows.Forms.DataGrid datagdColumn;
		private System.Windows.Forms.DataGrid datagdKey;
		private System.Windows.Forms.DataGrid datagdCheck;
		private System.Windows.Forms.DataGrid datagdIndex;
		private System.Windows.Forms.DataGrid datagdPriviledge;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public frmTables()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			DataTable column_table = CreateDataTableColumn();
			//MessageBox.Show(B.Columns.Count.ToString());
			datagdColumn.DataSource = column_table;

			DataGridTableStyle ts1 = new DataGridTableStyle() ;
			ts1.MappingName = "Column_Table";
   
			DataGridTextBoxColumn column = new DataGridTextBoxColumn() ;
			column.MappingName = "Name" ;
			column.HeaderText = "Name";
			column.Width = 80 ;
			ts1.GridColumnStyles.Add ( column ) ;

			DataGridComboBoxColumn column2 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged)); 
			column2.MappingName = "Type";
			column2.HeaderText = "Type";
			column2.Width = 100 ;
			ts1.GridColumnStyles.Add ( column2 ) ;
			
			DataGridBoolColumn bcol1 = new DataGridBoolColumn() ;
			bcol1.MappingName = "Nullable";
			bcol1.HeaderText = "Nullable";
			bcol1.Width = 80 ;
			bcol1.AllowNull = false ;
			ts1.GridColumnStyles.Add (bcol1) ;
			
	
			DataGridTextBoxColumn column3 = new DataGridTextBoxColumn() ;
			column3.MappingName = "Default" ;
			column3.HeaderText = "Default";
			column3.Width = 80 ;
			ts1.GridColumnStyles.Add ( column3 ) ;

			DataGridTextBoxColumn column4 = new DataGridTextBoxColumn() ;
			column4.MappingName = "Comments" ;
			column4.HeaderText = "Comments";
			column4.Width = 80 ;
			ts1.GridColumnStyles.Add ( column4 ) ;


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
			
			datagdColumn.TableStyles.Add(ts1);
			


			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// 
		//combo box code 
		public void MyComboValueChanged(int rowChanging, object newValue)
		{
			Console.WriteLine("index changed {0} {1}", rowChanging, newValue);
		}


		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if(components != null)
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.panTable = new System.Windows.Forms.Panel();
			this.tabTable = new System.Windows.Forms.TabControl();
			this.tabpgColumn = new System.Windows.Forms.TabPage();
			this.datagdColumn = new System.Windows.Forms.DataGrid();
			this.tabpgKey = new System.Windows.Forms.TabPage();
			this.datagdKey = new System.Windows.Forms.DataGrid();
			this.tabpgCheck = new System.Windows.Forms.TabPage();
			this.datagdCheck = new System.Windows.Forms.DataGrid();
			this.tabpgIndex = new System.Windows.Forms.TabPage();
			this.datagdIndex = new System.Windows.Forms.DataGrid();
			this.tabpgPriviledge = new System.Windows.Forms.TabPage();
			this.datagdPriviledge = new System.Windows.Forms.DataGrid();
			this.tbTable = new System.Windows.Forms.ToolBar();
			this.panTableLower = new System.Windows.Forms.Panel();
			this.btnViewSql = new System.Windows.Forms.Button();
			this.btnQuery = new System.Windows.Forms.Button();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnApply = new System.Windows.Forms.Button();
			this.panTableUpper = new System.Windows.Forms.Panel();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.label4 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.txtOwner = new System.Windows.Forms.TextBox();
			this.txtName = new System.Windows.Forms.TextBox();
			this.panTable.SuspendLayout();
			this.tabTable.SuspendLayout();
			this.tabpgColumn.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdColumn)).BeginInit();
			this.tabpgKey.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdKey)).BeginInit();
			this.tabpgCheck.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdCheck)).BeginInit();
			this.tabpgIndex.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdIndex)).BeginInit();
			this.tabpgPriviledge.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdPriviledge)).BeginInit();
			this.panTableLower.SuspendLayout();
			this.panTableUpper.SuspendLayout();
			this.groupBox1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panTable
			// 
			this.panTable.Controls.Add(this.tabTable);
			this.panTable.Controls.Add(this.tbTable);
			this.panTable.Controls.Add(this.panTableLower);
			this.panTable.Controls.Add(this.panTableUpper);
			this.panTable.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panTable.Location = new System.Drawing.Point(0, 0);
			this.panTable.Name = "panTable";
			this.panTable.Size = new System.Drawing.Size(792, 461);
			this.panTable.TabIndex = 0;
			// 
			// tabTable
			// 
			this.tabTable.Controls.Add(this.tabpgColumn);
			this.tabTable.Controls.Add(this.tabpgKey);
			this.tabTable.Controls.Add(this.tabpgCheck);
			this.tabTable.Controls.Add(this.tabpgIndex);
			this.tabTable.Controls.Add(this.tabpgPriviledge);
			this.tabTable.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tabTable.Location = new System.Drawing.Point(0, 56);
			this.tabTable.Name = "tabTable";
			this.tabTable.SelectedIndex = 0;
			this.tabTable.Size = new System.Drawing.Size(753, 349);
			this.tabTable.TabIndex = 3;
			// 
			// tabpgColumn
			// 
			this.tabpgColumn.Controls.Add(this.datagdColumn);
			this.tabpgColumn.Location = new System.Drawing.Point(4, 22);
			this.tabpgColumn.Name = "tabpgColumn";
			this.tabpgColumn.Size = new System.Drawing.Size(745, 323);
			this.tabpgColumn.TabIndex = 0;
			this.tabpgColumn.Text = "Columns";
			// 
			// datagdColumn
			// 
			this.datagdColumn.DataMember = "";
			this.datagdColumn.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdColumn.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdColumn.Location = new System.Drawing.Point(0, 0);
			this.datagdColumn.Name = "datagdColumn";
			this.datagdColumn.Size = new System.Drawing.Size(745, 323);
			this.datagdColumn.TabIndex = 0;
			// 
			// tabpgKey
			// 
			this.tabpgKey.Controls.Add(this.datagdKey);
			this.tabpgKey.Location = new System.Drawing.Point(4, 22);
			this.tabpgKey.Name = "tabpgKey";
			this.tabpgKey.Size = new System.Drawing.Size(457, 171);
			this.tabpgKey.TabIndex = 1;
			this.tabpgKey.Text = "Keys";
			// 
			// datagdKey
			// 
			this.datagdKey.DataMember = "";
			this.datagdKey.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdKey.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdKey.Location = new System.Drawing.Point(0, 0);
			this.datagdKey.Name = "datagdKey";
			this.datagdKey.Size = new System.Drawing.Size(457, 171);
			this.datagdKey.TabIndex = 0;
			// 
			// tabpgCheck
			// 
			this.tabpgCheck.Controls.Add(this.datagdCheck);
			this.tabpgCheck.Location = new System.Drawing.Point(4, 22);
			this.tabpgCheck.Name = "tabpgCheck";
			this.tabpgCheck.Size = new System.Drawing.Size(457, 171);
			this.tabpgCheck.TabIndex = 2;
			this.tabpgCheck.Text = "Checks";
			// 
			// datagdCheck
			// 
			this.datagdCheck.DataMember = "";
			this.datagdCheck.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdCheck.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdCheck.Location = new System.Drawing.Point(0, 0);
			this.datagdCheck.Name = "datagdCheck";
			this.datagdCheck.Size = new System.Drawing.Size(457, 171);
			this.datagdCheck.TabIndex = 0;
			// 
			// tabpgIndex
			// 
			this.tabpgIndex.Controls.Add(this.datagdIndex);
			this.tabpgIndex.Location = new System.Drawing.Point(4, 22);
			this.tabpgIndex.Name = "tabpgIndex";
			this.tabpgIndex.Size = new System.Drawing.Size(457, 171);
			this.tabpgIndex.TabIndex = 3;
			this.tabpgIndex.Text = "Indexes";
			// 
			// datagdIndex
			// 
			this.datagdIndex.DataMember = "";
			this.datagdIndex.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdIndex.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdIndex.Location = new System.Drawing.Point(0, 0);
			this.datagdIndex.Name = "datagdIndex";
			this.datagdIndex.Size = new System.Drawing.Size(457, 171);
			this.datagdIndex.TabIndex = 0;
			// 
			// tabpgPriviledge
			// 
			this.tabpgPriviledge.Controls.Add(this.datagdPriviledge);
			this.tabpgPriviledge.Location = new System.Drawing.Point(4, 22);
			this.tabpgPriviledge.Name = "tabpgPriviledge";
			this.tabpgPriviledge.Size = new System.Drawing.Size(457, 171);
			this.tabpgPriviledge.TabIndex = 4;
			this.tabpgPriviledge.Text = "Priviledges";
			// 
			// datagdPriviledge
			// 
			this.datagdPriviledge.DataMember = "";
			this.datagdPriviledge.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdPriviledge.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdPriviledge.Location = new System.Drawing.Point(0, 0);
			this.datagdPriviledge.Name = "datagdPriviledge";
			this.datagdPriviledge.Size = new System.Drawing.Size(457, 171);
			this.datagdPriviledge.TabIndex = 0;
			// 
			// tbTable
			// 
			this.tbTable.Dock = System.Windows.Forms.DockStyle.Right;
			this.tbTable.DropDownArrows = true;
			this.tbTable.Location = new System.Drawing.Point(753, 56);
			this.tbTable.Name = "tbTable";
			this.tbTable.ShowToolTips = true;
			this.tbTable.Size = new System.Drawing.Size(39, 349);
			this.tbTable.TabIndex = 2;
			// 
			// panTableLower
			// 
			this.panTableLower.Controls.Add(this.btnViewSql);
			this.panTableLower.Controls.Add(this.btnQuery);
			this.panTableLower.Controls.Add(this.btnClose);
			this.panTableLower.Controls.Add(this.btnApply);
			this.panTableLower.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.panTableLower.Location = new System.Drawing.Point(0, 405);
			this.panTableLower.Name = "panTableLower";
			this.panTableLower.Size = new System.Drawing.Size(792, 56);
			this.panTableLower.TabIndex = 1;
			// 
			// btnViewSql
			// 
			this.btnViewSql.Location = new System.Drawing.Point(320, 16);
			this.btnViewSql.Name = "btnViewSql";
			this.btnViewSql.Size = new System.Drawing.Size(64, 24);
			this.btnViewSql.TabIndex = 3;
			this.btnViewSql.Text = "View SQL";
			// 
			// btnQuery
			// 
			this.btnQuery.Location = new System.Drawing.Point(224, 16);
			this.btnQuery.Name = "btnQuery";
			this.btnQuery.TabIndex = 2;
			this.btnQuery.Text = "Query";
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(128, 16);
			this.btnClose.Name = "btnClose";
			this.btnClose.TabIndex = 1;
			this.btnClose.Text = "Close";
			// 
			// btnApply
			// 
			this.btnApply.Location = new System.Drawing.Point(32, 16);
			this.btnApply.Name = "btnApply";
			this.btnApply.TabIndex = 0;
			this.btnApply.Text = "Apply";
			// 
			// panTableUpper
			// 
			this.panTableUpper.Controls.Add(this.groupBox1);
			this.panTableUpper.Dock = System.Windows.Forms.DockStyle.Top;
			this.panTableUpper.Location = new System.Drawing.Point(0, 0);
			this.panTableUpper.Name = "panTableUpper";
			this.panTableUpper.Size = new System.Drawing.Size(792, 56);
			this.panTableUpper.TabIndex = 0;
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.label4);
			this.groupBox1.Controls.Add(this.label3);
			this.groupBox1.Controls.Add(this.txtOwner);
			this.groupBox1.Controls.Add(this.txtName);
			this.groupBox1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.groupBox1.Location = new System.Drawing.Point(0, 0);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(792, 56);
			this.groupBox1.TabIndex = 0;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "groupBox1";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(264, 24);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(51, 20);
			this.label4.TabIndex = 3;
			this.label4.Text = "Owner :";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(40, 24);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(51, 20);
			this.label3.TabIndex = 2;
			this.label3.Text = "Name :";
			// 
			// txtOwner
			// 
			this.txtOwner.Location = new System.Drawing.Point(328, 24);
			this.txtOwner.Name = "txtOwner";
			this.txtOwner.TabIndex = 1;
			this.txtOwner.Text = "";
			// 
			// txtName
			// 
			this.txtName.Location = new System.Drawing.Point(104, 24);
			this.txtName.Name = "txtName";
			this.txtName.TabIndex = 0;
			this.txtName.Text = "";
			// 
			// frmTables
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(792, 461);
			this.Controls.Add(this.panTable);
			this.Name = "frmTables";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
			this.panTable.ResumeLayout(false);
			this.tabTable.ResumeLayout(false);
			this.tabpgColumn.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdColumn)).EndInit();
			this.tabpgKey.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdKey)).EndInit();
			this.tabpgCheck.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdCheck)).EndInit();
			this.tabpgIndex.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdIndex)).EndInit();
			this.tabpgPriviledge.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdPriviledge)).EndInit();
			this.panTableLower.ResumeLayout(false);
			this.panTableUpper.ResumeLayout(false);
			this.groupBox1.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion

		private DataTable CreateDataTableColumn()
		{
			DataTable aTable = new DataTable("Column_Table");
			DataColumn dtCol;
			DataRow dtRow; 
			// Create ID column and add to the DataTable.
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Name";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Name";
			dtCol.ReadOnly = false;
			dtCol.Unique = false;

			// Add the column to the DataColumnCollection.
			aTable.Columns.Add(dtCol);

			// Create Name column and add to the table
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Type";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Type";
			dtCol.ReadOnly = false;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.Boolean");
			dtCol.ColumnName = "Nullable";
			//dtCol.AutoIncrement = false;
			dtCol.Caption = "Nullable";
			dtCol.ReadOnly = false;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			// Create Last Name column and add to the table.
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Default";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Default";
			dtCol.ReadOnly = false;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			//// Create Last Name column and add to the table.
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Comments";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Comments";
			dtCol.ReadOnly = false;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);


			// Create on blank rows to the table
			dtRow = aTable.NewRow();
			dtRow["Name"] = "Employee_id";
			dtRow["Type"] = "String";
			dtRow["Nullable"] = false;
			dtRow["Default"] = "1";
			dtRow["Comments"] = "Emp_id";
			
			aTable.Rows.Add(dtRow);
			return aTable;
		}
	}

	//Code for Combobox in DataGrid

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


	public class NoKeyUpCombo : ComboBox
	{
		const int WM_KEYUP = 0x101;
		protected override void WndProc(ref System.Windows.Forms.Message m)
		{
			if(m.Msg == WM_KEYUP)
			{
				//ignore keyup to avoid problem with tabbing & dropdownlist;
				return;
			}
			base.WndProc(ref m);
		}
	}



}
