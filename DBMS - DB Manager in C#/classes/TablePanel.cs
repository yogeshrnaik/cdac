using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using DBManager.forms;
using System.Text.RegularExpressions;
/********************************************************************************/
namespace DBManager.classes
{
	/********************************************************************************/
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	/********************************************************************************/
	public class TablePanel : Panel
	{
		/********************************************************************************/
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
		private System.Windows.Forms.DataGrid datagdColumn;
		private System.Windows.Forms.DataGrid datagdKey;
		private System.Windows.Forms.DataGrid datagdCheck;
		private System.Windows.Forms.DataGrid datagdIndex;
		private System.Windows.Forms.DataGrid datagdPriviledge;
		private System.Windows.Forms.RichTextBox rtxtTable;
		private System.Windows.Forms.CheckBox chkbSQL;
		private System.Windows.Forms.RichTextBox rtxtTable1;
		private System.Windows.Forms.TabPage tabpgData;
		private System.Windows.Forms.DataGrid datagdData;
		private System.Windows.Forms.ToolBarButton tbbtnAdd;
		private System.Windows.Forms.ToolBarButton tbbtnDelete;
		private System.ComponentModel.Container components = null;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		/// 
		public string tableName;
		public string schema_name;
		public string created_by;
		public Database database;
		public TabPage myTabPage;
		private Table TableTmp;
		
		
		public TablePanel(Database database, string schema, string tableName, string username)
		{
			InitializeComponent();
			this.Dock = DockStyle.Fill;
			this.database = database;
			this.schema_name = schema;
			this.tableName = tableName;
			this.created_by = username;

			populate_table_data();
			
			this.datagdColumn.DataSource = TableTmp.table_columns;
			this.datagdColumn.TableStyles.Add(TableTmp.getTableStyleColumn());
			//this.datagdColumn.MouseDown += new MouseEventHandler(datagdColumn_MouseDown);

			this.datagdKey.DataSource = TableTmp.keys;
			this.datagdKey.TableStyles.Add(TableTmp.getTableStyleKeys());

			this.datagdCheck.DataSource = TableTmp.checks;

			this.datagdIndex.DataSource = TableTmp.indexes;

			this.datagdPriviledge.DataSource = TableTmp.priviledges;

		}
		/********************************************************************************/		
		private void InitializeComponent()
		{
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
			this.chkbSQL = new System.Windows.Forms.CheckBox();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnApply = new System.Windows.Forms.Button();
			this.panTableUpper = new System.Windows.Forms.Panel();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.label4 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.txtOwner = new System.Windows.Forms.TextBox();
			this.txtName = new System.Windows.Forms.TextBox();
			this.rtxtTable = new System.Windows.Forms.RichTextBox();
			this.rtxtTable1 = new System.Windows.Forms.RichTextBox();
			this.tabpgData = new System.Windows.Forms.TabPage();
			this.datagdData = new System.Windows.Forms.DataGrid();
			this.tbbtnAdd = new System.Windows.Forms.ToolBarButton();
			this.tbbtnDelete = new System.Windows.Forms.ToolBarButton();
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
			this.tabpgData.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.datagdData)).BeginInit();
			this.SuspendLayout();
			// 
			// panTable
			// 
			this.Controls.Add(this.tabTable);
			this.Controls.Add(this.tbTable);
			this.Controls.Add(this.panTableLower);
			this.Controls.Add(this.panTableUpper);
			this.Controls.Add(this.rtxtTable);
			
			// 
			// tabTable
			// 
			this.tabTable.Controls.Add(this.tabpgColumn);
			this.tabTable.Controls.Add(this.tabpgKey);
			this.tabTable.Controls.Add(this.tabpgCheck);
			this.tabTable.Controls.Add(this.tabpgIndex);
			this.tabTable.Controls.Add(this.tabpgPriviledge);
			this.tabTable.Controls.Add(this.tabpgData);
			this.tabTable.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tabTable.Location = new System.Drawing.Point(0, 88);
			this.tabTable.Name = "tabTable";
			this.tabTable.SelectedIndex = 0;
			this.tabTable.Size = new System.Drawing.Size(588, 237);
			this.tabTable.TabIndex = 3;
			// 
			// tabpgColumn
			// 
			this.tabpgColumn.Controls.Add(this.datagdColumn);
			this.tabpgColumn.Location = new System.Drawing.Point(4, 22);
			this.tabpgColumn.Name = "tabpgColumn";
			this.tabpgColumn.Size = new System.Drawing.Size(577, 211);
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
			this.datagdColumn.Size = new System.Drawing.Size(577, 211);
			this.datagdColumn.TabIndex = 0;
			// 
			// tabpgKey
			// 
			this.tabpgKey.Controls.Add(this.datagdKey);
			this.tabpgKey.Location = new System.Drawing.Point(4, 22);
			this.tabpgKey.Name = "tabpgKey";
			this.tabpgKey.Size = new System.Drawing.Size(577, 211);
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
			this.datagdKey.Size = new System.Drawing.Size(577, 211);
			this.datagdKey.TabIndex = 0;
			// 
			// tabpgCheck
			// 
			this.tabpgCheck.Controls.Add(this.datagdCheck);
			this.tabpgCheck.Location = new System.Drawing.Point(4, 22);
			this.tabpgCheck.Name = "tabpgCheck";
			this.tabpgCheck.Size = new System.Drawing.Size(577, 211);
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
			this.datagdCheck.Size = new System.Drawing.Size(577, 211);
			this.datagdCheck.TabIndex = 0;
			// 
			// tabpgIndex
			// 
			this.tabpgIndex.Controls.Add(this.datagdIndex);
			this.tabpgIndex.Location = new System.Drawing.Point(4, 22);
			this.tabpgIndex.Name = "tabpgIndex";
			this.tabpgIndex.Size = new System.Drawing.Size(577, 211);
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
			this.datagdIndex.Size = new System.Drawing.Size(577, 211);
			this.datagdIndex.TabIndex = 0;
			// 
			// tabpgPriviledge
			// 
			this.tabpgPriviledge.Controls.Add(this.datagdPriviledge);
			this.tabpgPriviledge.Location = new System.Drawing.Point(4, 22);
			this.tabpgPriviledge.Name = "tabpgPriviledge";
			this.tabpgPriviledge.Size = new System.Drawing.Size(577, 211);
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
			this.datagdPriviledge.Size = new System.Drawing.Size(577, 211);
			this.datagdPriviledge.TabIndex = 0;
			// 
			// tbTable
			// 
			this.tbTable.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																					   this.tbbtnAdd,
																					   this.tbbtnDelete});
			this.tbTable.Dock = System.Windows.Forms.DockStyle.Right;
			this.tbTable.DropDownArrows = true;
			this.tbTable.Location = new System.Drawing.Point(588, 88);
			this.tbTable.Name = "tbTable";
			this.tbTable.ShowToolTips = true;
			this.tbTable.Size = new System.Drawing.Size(20, 237);
			this.tbTable.TabIndex = 2;
			this.tbTable.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.tbTable_ButtonClick);
			// 
			// panTableLower
			// 
			this.panTableLower.Controls.Add(this.chkbSQL);
			this.panTableLower.Controls.Add(this.btnClose);
			this.panTableLower.Controls.Add(this.btnApply);
			this.panTableLower.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.panTableLower.Location = new System.Drawing.Point(0, 325);
			this.panTableLower.Name = "panTableLower";
			this.panTableLower.Size = new System.Drawing.Size(608, 56);
			this.panTableLower.TabIndex = 1;
			// 
			// chkbSQL
			// 
			this.chkbSQL.Appearance = System.Windows.Forms.Appearance.Button;
			this.chkbSQL.Location = new System.Drawing.Point(216, 16);
			this.chkbSQL.Name = "chkbSQL";
			this.chkbSQL.Size = new System.Drawing.Size(72, 24);
			this.chkbSQL.TabIndex = 3;
			this.chkbSQL.Text = "View SQL";
			this.chkbSQL.CheckedChanged += new System.EventHandler(this.chkbSQL_CheckedChanged);
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(120, 16);
			this.btnClose.Name = "btnClose";
			this.btnClose.TabIndex = 1;
			this.btnClose.Text = "Close";
			this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
			// 
			// btnApply
			// 
			this.btnApply.Location = new System.Drawing.Point(24, 16);
			this.btnApply.Name = "btnApply";
			this.btnApply.TabIndex = 0;
			this.btnApply.Text = "Apply";
			this.btnApply.Click += new System.EventHandler(this.btnApply_Click);
			// 
			// panTableUpper
			// 
			this.panTableUpper.Controls.Add(this.groupBox1);
			this.panTableUpper.Dock = System.Windows.Forms.DockStyle.Top;
			this.panTableUpper.Location = new System.Drawing.Point(0, 0);
			this.panTableUpper.Name = "panTableUpper";
			this.panTableUpper.Size = new System.Drawing.Size(608, 88);
			this.panTableUpper.TabIndex = 0;
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.label4);
			this.groupBox1.Controls.Add(this.label3);
			this.groupBox1.Controls.Add(this.txtOwner);
			this.groupBox1.Controls.Add(this.txtName);
			this.groupBox1.Location = new System.Drawing.Point(8, 24);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(600, 56);
			this.groupBox1.TabIndex = 0;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "Table Details";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(360, 24);
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
			this.txtOwner.Location = new System.Drawing.Point(416, 24);
			this.txtOwner.Name = "txtOwner";
			this.txtOwner.ReadOnly = true;
			this.txtOwner.Size = new System.Drawing.Size(152, 20);
			this.txtOwner.TabIndex = 1;
			this.txtOwner.Text = "";
			// 
			// txtName
			// 
			this.txtName.Location = new System.Drawing.Point(104, 24);
			this.txtName.Name = "txtName";
			this.txtName.Size = new System.Drawing.Size(152, 20);
			this.txtName.TabIndex = 0;
			this.txtName.Text = "";
			// 
			// rtxtTable
			// 
			this.rtxtTable.Location = new System.Drawing.Point(0, 88);
			this.rtxtTable.Name = "rtxtTable";
			this.rtxtTable.ReadOnly = true;
			this.rtxtTable.Size = new System.Drawing.Size(569, 237);
			this.rtxtTable.TabIndex = 1;
			this.rtxtTable.Text = "";
			this.rtxtTable.Visible = false;
			// 
			// rtxtTable1
			// 
			this.rtxtTable1.Location = new System.Drawing.Point(0, 0);
			this.rtxtTable1.Name = "rtxtTable1";
			this.rtxtTable1.TabIndex = 0;
			this.rtxtTable1.Text = "";
			// 
			// tabpgData
			// 
			this.tabpgData.Controls.Add(this.datagdData);
			this.tabpgData.Location = new System.Drawing.Point(4, 22);
			this.tabpgData.Name = "tabpgData";
			this.tabpgData.Size = new System.Drawing.Size(580, 211);
			this.tabpgData.TabIndex = 5;
			this.tabpgData.Text = "Data";
			// 
			// datagdData
			// 
			this.datagdData.DataMember = "";
			this.datagdData.Dock = System.Windows.Forms.DockStyle.Fill;
			this.datagdData.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.datagdData.Location = new System.Drawing.Point(0, 0);
			this.datagdData.Name = "datagdData";
			this.datagdData.Size = new System.Drawing.Size(580, 211);
			this.datagdData.TabIndex = 0;
			// 
			// tbbtnAdd
			// 
			this.tbbtnAdd.Text = "A";
			// 
			// tbbtnDelete
			// 
			this.tbbtnDelete.Text = "D";
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
			this.tabpgData.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.datagdData)).EndInit();
			this.ResumeLayout(false);

		}
		
		/********************************************************************************/
		/*	private void datagdColumn_MouseDown(object sender, System.Windows.Forms.MouseEventArgs me)
			{
				DataTable dt = (DataTable)this.datagdColumn.DataSource;
				//dt.Columns["Save_passwd"].ReadOnly = false;
				//dt.Columns["Auto_conn"].ReadOnly = false;
				int l_row = this.datagdColumn.HitTest(me.X,me.Y).Row;
				int l_col = this.datagdColumn.HitTest(me.X,me.Y).Column;
				if (l_row > -1 && l_row < dt.Rows.Count && l_col > 2 && l_col <= dt.Columns.Count)
				{
					//if (dt.Rows[l_row][l_col].Equals(false))
					if (datagdColumn[l_row,l_col].Equals(false))
					//
					{
						dt.Rows[l_row][l_col] = true;
					}
					else
					{
						dt.Rows[l_row][l_col] = false;
					}
				}
			}*/
		/********************************************************************************/
		private void chkbSQL_CheckedChanged(object sender, System.EventArgs e)
		{
			if (this.chkbSQL.Checked)
			{
				this.rtxtTable.Visible = true;
				this.tabTable.Visible = false;
				this.rtxtTable.ReadOnly = false;
				this.rtxtTable.Text= createTableSQL();
				//MessageBox.Show(this.rtxtTable.Text);
				Parse(rtxtTable);
				this.rtxtTable.ReadOnly = true;
			}
			else 
			{
				this.rtxtTable.Visible = false;
				this.tabTable.Visible = true;
			}
		}
		public string createTableSQL()
		{
			string table_sql = "";
			DataTable table_columns = (DataTable)this.datagdColumn.DataSource;
			table_sql = "create table " + this.txtName.Text + " ( "; 
			for(int i=0; i < table_columns.Rows.Count-1 ; i++)
			{
				
				table_sql = table_sql + "\n" + 
					table_columns.Rows[i][0] + " " + 
					table_columns.Rows[i][1] + "," ;
			}
			table_sql = table_sql + "\n" + 
				table_columns.Rows[table_columns.Rows.Count-1][1] + " " + 
				table_columns.Rows[table_columns.Rows.Count-1][2];
			table_sql = "\n" + table_sql + ")";
			return table_sql;
		}
		/********************************************************************************/
		private void btnClose_Click(object sender, System.EventArgs e)
		{
			//removing the correspoinding tree node
			TreeNode tn = (TreeNode)myTabPage.Tag;
			ConnectionTreeNode conTN = (ConnectionTreeNode)tn.Parent;
			conTN.Nodes.Remove(tn);
			conTN.closeWindow(myTabPage);

			//removing the tab page
			this.myTabPage.Hide();
			TabControl tc = (TabControl)this.myTabPage.Parent;
			//tc.TabPages.Remove(this.myTabPage);
		}
		/********************************************************************************/
		private void btnApply_Click(object sender, System.EventArgs e)
		{
			string msg = "";
			if (this.tableName.Length == 0)
				msg = "Add the Table, are you sure?";
			else
				msg = "Update the Table, are you sure?";

			if(MessageBox.Show(msg, "DB Manager", MessageBoxButtons.OKCancel,
				MessageBoxIcon.Question) == DialogResult.OK)
			{
				if(tableName.Length == 0)
				{
					Table newTable = new Table(txtName.Text, created_by, System.DateTime.Now);
					database.getSchema(schema_name).addTable(newTable);

					//now update the table
					DataTable dt_columns = (DataTable)this.datagdColumn.DataSource;
					DataTable dt_keys = (DataTable)this.datagdKey.DataSource;
					DataTable dt_checks = (DataTable)this.datagdCheck.DataSource;
					DataTable dt_priviledges = (DataTable)this.datagdPriviledge.DataSource;
					DataTable dt_data = (DataTable)this.datagdData.DataSource;
					DataTable dt_indexes = (DataTable)this.datagdIndex.DataSource;
					//database.up
					database.updateTable(schema_name,txtName.Text,
						dt_columns,dt_keys,dt_checks,dt_indexes,
						dt_data,dt_priviledges);
					
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm)tc.Parent;
					
					this.tableName = this.txtName.Text;

					TreeNode tn = main.getDBObjectNode("TABLE");
					if (tn != null)
					{
						TreeNode tableNode = new TreeNode(this.txtName.Text,
							MyImageList.getImgList().getImageIndex("TABLE"),
							MyImageList.getImgList().getImageIndex("TABLE"));
						tableNode.Tag = "TABLE";
						tn.Nodes.Add(tableNode);
						tn.Expand();
						tn.Text = "Tables (" + main.getActiveConnection().database.getSchema(schema_name).tables.Count + ")";
					}
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Table added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						//this.btnRefresh.Enabled = false;
						this.txtName.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not add Table.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
				else 
				{
					DataTable dt_columns = (DataTable)this.datagdColumn.DataSource;
					DataTable dt_keys = (DataTable)this.datagdKey.DataSource;
					DataTable dt_checks = (DataTable)this.datagdCheck.DataSource;
					DataTable dt_priviledges = (DataTable)this.datagdPriviledge.DataSource;
					DataTable dt_data = (DataTable)this.datagdData.DataSource;
					DataTable dt_indexes = (DataTable)this.datagdIndex.DataSource;
					//database.up
					database.updateTable(schema_name,txtName.Text,dt_columns,dt_keys,dt_checks,dt_indexes,dt_data,dt_priviledges);
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm) tc.Parent;
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Table updated successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						//this.btnRefresh.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not update Table.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
			}
		}
		/********************************************************************************/
		void Parse(RichTextBox sqlTextBox) 
		{
			String inputLanguage = sqlTextBox.Text;
			sqlTextBox.Text="";
			//ParseLine(sqlTextBox,inputLanguage);
			Regex r = new Regex("\\n");
			String [] lines = r.Split(inputLanguage);
			foreach (string l in lines) 
			{
				ParseLine(sqlTextBox,l);
			}    
		}
		/********************************************************************************/
		void ParseLine(RichTextBox sqlTextBox,string line) 
		{
			Regex r = new Regex("([ \\t{}();])");
			String [] tokens = r.Split(line);

			foreach (string token in tokens) 
			{
				// Set the token's default color and font.
				sqlTextBox.SelectionColor = Color.Black;

				// Check for a comment.
				if (token == "--" || token.StartsWith("--")) 
				{
					// Find the start of the comment and then extract the whole comment.
					int index = line.IndexOf("--");
					string comment = line.Substring(index, line.Length - index);
					sqlTextBox.SelectionColor = Color.Green;
					sqlTextBox.SelectedText = comment;
					break;
				}
				/*
				if (token == "'" || token.StartsWith("'")) 
				{
					// Find the start of the comment and then extract the whole comment.
					int index = line.IndexOf("'");
					string comment1 = line.Substring(index, line.Length - index);
					if (flag)
					{
						sqlTextBox.SelectionColor = Color.Red;
						flag=false;
					}
					else
					{
						flag=true;
					}
					sqlTextBox.SelectedText = comment1;
					break;
				}
				*/      
				// Check whether the token is a keyword. 
				String [] keywords = { "select", "from", "where", "like", "and" , "or", "create", "table" }; 
				for (int i = 0; i < keywords.Length; i++) 
				{
					if (keywords[i].ToLower().Equals(token.ToLower()))
					{
						// Apply alternative color and font to highlight keyword.
						sqlTextBox.SelectionColor = Color.Blue;
						break;
					}
				}
				sqlTextBox.SelectedText = token;
			}
		}

		/********************************************************************************/
		private void tbTable_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			ToolBarButton tbbClicked = (ToolBarButton)e.Button;
			if (tbbClicked.Text == "A")
			{
				MessageBox.Show("Add");
			}
			else if (tbbClicked.Text == "D")
			{
				MessageBox.Show("Delete");
			}
		}

		/********************************************************************************/
		public void populate_table_data()
		{
			this.txtOwner.Text = this.created_by;
			if (tableName.Length == 0)
			{
				//new table
				this.txtName.Enabled = true;
				TableTmp = new Table();
			}
			else 
			{
				this.txtName.Text = this.tableName;
				this.txtName.Enabled = false;
				TableTmp = database.getTable(this.schema_name,this.tableName);
			}
		}
		/********************************************************************************/
	}
	/********************************************************************************/
	
	
}
