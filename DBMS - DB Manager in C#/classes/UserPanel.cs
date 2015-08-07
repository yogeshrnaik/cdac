using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using DBManager.forms;

namespace DBManager.classes
{
	/// <summary>
	/// Summary description for UserPanel.
	/// </summary>
	public class UserPanel :Panel
	{
		
		private System.Windows.Forms.Button btnApply;
		private System.Windows.Forms.Button btnRefresh;
		private System.Windows.Forms.Button btnClose;
		private System.Windows.Forms.Button btnHelp;
		private System.Windows.Forms.CheckBox chkViewSQL;
		private System.Windows.Forms.TabPage tabGeneral;
		private System.Windows.Forms.RichTextBox txtSQLGeneral;
		private System.Windows.Forms.CheckBox chkAccount;
		private System.Windows.Forms.CheckBox chkPwd;
		private System.Windows.Forms.CheckBox chkIdentify;
		private System.Windows.Forms.ComboBox cmbProfile;
		private System.Windows.Forms.ComboBox cmbTempTablespace;
		private System.Windows.Forms.ComboBox cmbTablespace;
		private System.Windows.Forms.TextBox txtPwd;
		private System.Windows.Forms.TextBox txtName;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.TabPage tabObj;
		private System.Windows.Forms.DataGrid dtgridConnHist;
		private System.Windows.Forms.TabControl UserTab;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.Label label6;
		public string userName;
		public string schema_name;
		public string created_by;
		public Database database;
		public TabPage myTabPage;
		
		
		public void MyComboValueChanged(int rowChanging, object newValue)
		{
			Console.WriteLine("index changed {0} {1}", rowChanging, newValue);
		}
		public UserPanel(Database database, string schema, string userName, string creator)
		{
			InitializeComponent();
			
			this.database = database;
			this.schema_name = schema;
			this.userName = userName;
			this.created_by=creator;
			
			
			DataTable B = CreateDataTableA();
			
			dtgridConnHist.DataSource = B;
			DataGridTableStyle ts = new DataGridTableStyle() ;
			ts.MappingName = "A";
   
			DataGridComboBoxColumn column1 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged));
			column1.MappingName = "Object" ;
			column1.HeaderText = "Object";
			column1.Width = 80 ;
			ts.GridColumnStyles.Add ( column1 ) ;

			DataGridComboBoxColumn column2 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged));
			column2.MappingName = "Select" ;
			column2.HeaderText = "Select";
			column2.Width = 80 ;
			ts.GridColumnStyles.Add ( column2 ) ;

			DataGridComboBoxColumn column3 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged));
			column3.MappingName = "Insert" ;
			column3.HeaderText = "Insert";
			column3.Width = 80 ;
			ts.GridColumnStyles.Add ( column3 ) ;

			DataGridComboBoxColumn column4 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged));
			column4.MappingName = "Update" ;
			column4.HeaderText = "Update";
			column4.Width = 80 ;
			ts.GridColumnStyles.Add ( column4 ) ;

			DataGridComboBoxColumn column5 = new DataGridComboBoxColumn(new ComboValueChanged(MyComboValueChanged));
			column5.MappingName = "Delete" ;
			column5.HeaderText = "Delete";
			column5.Width = 80 ;
			ts.GridColumnStyles.Add ( column5 ) ;



 
 
			

			dtgridConnHist.TableStyles.Add(ts);
			this.Dock = DockStyle.Fill;
		
		
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			
			this.label6 = new System.Windows.Forms.Label();
			this.chkViewSQL = new System.Windows.Forms.CheckBox();
			this.btnHelp = new System.Windows.Forms.Button();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnRefresh = new System.Windows.Forms.Button();
			this.btnApply = new System.Windows.Forms.Button();
			this.UserTab = new System.Windows.Forms.TabControl();
			this.tabGeneral = new System.Windows.Forms.TabPage();
			this.txtSQLGeneral = new System.Windows.Forms.RichTextBox();
			this.chkAccount = new System.Windows.Forms.CheckBox();
			this.chkPwd = new System.Windows.Forms.CheckBox();
			this.chkIdentify = new System.Windows.Forms.CheckBox();
			this.cmbProfile = new System.Windows.Forms.ComboBox();
			this.cmbTempTablespace = new System.Windows.Forms.ComboBox();
			this.cmbTablespace = new System.Windows.Forms.ComboBox();
			this.txtPwd = new System.Windows.Forms.TextBox();
			this.txtName = new System.Windows.Forms.TextBox();
			this.label4 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.tabObj = new System.Windows.Forms.TabPage();
			this.dtgridConnHist = new System.Windows.Forms.DataGrid();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			
			this.UserTab.SuspendLayout();
			this.tabGeneral.SuspendLayout();
			this.tabObj.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.dtgridConnHist)).BeginInit();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.Controls.Add(this.label6);
			this.Controls.Add(this.chkViewSQL);
			this.Controls.Add(this.btnHelp);
			this.Controls.Add(this.btnClose);
			this.Controls.Add(this.btnRefresh);
			this.Controls.Add(this.btnApply);
			this.Controls.Add(this.UserTab);
			this.Controls.Add(this.groupBox1);
			
			// 
			// label6
			// 
			this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label6.Location = new System.Drawing.Point(232, 16);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(264, 40);
			this.label6.TabIndex = 7;
			this.label6.Text = "USER";
			this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// chkViewSQL
			// 
			this.chkViewSQL.Appearance = System.Windows.Forms.Appearance.Button;
			this.chkViewSQL.Location = new System.Drawing.Point(592, 408);
			this.chkViewSQL.Name = "chkViewSQL";
			this.chkViewSQL.Size = new System.Drawing.Size(72, 24);
			this.chkViewSQL.TabIndex = 5;
			this.chkViewSQL.Text = "View SQL";
			this.chkViewSQL.CheckedChanged += new System.EventHandler(this.checkBox1_CheckedChanged);
			// 
			// btnHelp
			// 
			this.btnHelp.Location = new System.Drawing.Point(352, 408);
			this.btnHelp.Name = "btnHelp";
			this.btnHelp.Size = new System.Drawing.Size(72, 24);
			this.btnHelp.TabIndex = 4;
			this.btnHelp.Text = "Help";
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(264, 408);
			this.btnClose.Name = "btnClose";
			this.btnClose.Size = new System.Drawing.Size(56, 24);
			this.btnClose.TabIndex = 3;
			this.btnClose.Text = "Close";
			this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
			// 
			// btnRefresh
			// 
			this.btnRefresh.Location = new System.Drawing.Point(176, 408);
			this.btnRefresh.Name = "btnRefresh";
			this.btnRefresh.Size = new System.Drawing.Size(56, 24);
			this.btnRefresh.TabIndex = 2;
			this.btnRefresh.Text = "Refresh";
			// 
			// btnApply
			// 
			this.btnApply.Location = new System.Drawing.Point(80, 408);
			this.btnApply.Name = "btnApply";
			this.btnApply.Size = new System.Drawing.Size(64, 24);
			this.btnApply.TabIndex = 1;
			this.btnApply.Text = "Apply";
			// 
			// UserTab
			// 
			this.UserTab.Controls.Add(this.tabGeneral);
			this.UserTab.Controls.Add(this.tabObj);
			this.UserTab.ItemSize = new System.Drawing.Size(49, 18);
			this.UserTab.Location = new System.Drawing.Point(80, 96);
			this.UserTab.Name = "UserTab";
			this.UserTab.SelectedIndex = 0;
			this.UserTab.Size = new System.Drawing.Size(592, 296);
			this.UserTab.TabIndex = 0;
			// 
			// tabGeneral
			// 
			this.tabGeneral.Controls.Add(this.txtSQLGeneral);
			this.tabGeneral.Controls.Add(this.chkAccount);
			this.tabGeneral.Controls.Add(this.chkPwd);
			this.tabGeneral.Controls.Add(this.chkIdentify);
			this.tabGeneral.Controls.Add(this.cmbProfile);
			this.tabGeneral.Controls.Add(this.cmbTempTablespace);
			this.tabGeneral.Controls.Add(this.cmbTablespace);
			this.tabGeneral.Controls.Add(this.txtPwd);
			this.tabGeneral.Controls.Add(this.txtName);
			this.tabGeneral.Controls.Add(this.label4);
			this.tabGeneral.Controls.Add(this.label3);
			this.tabGeneral.Controls.Add(this.label2);
			this.tabGeneral.Controls.Add(this.label1);
			this.tabGeneral.Controls.Add(this.label5);
			this.tabGeneral.Location = new System.Drawing.Point(4, 22);
			this.tabGeneral.Name = "tabGeneral";
			this.tabGeneral.Size = new System.Drawing.Size(584, 270);
			this.tabGeneral.TabIndex = 0;
			this.tabGeneral.Text = "General";
			// 
			// txtSQLGeneral
			// 
			this.txtSQLGeneral.Location = new System.Drawing.Point(16, 16);
			this.txtSQLGeneral.Name = "txtSQLGeneral";
			this.txtSQLGeneral.Size = new System.Drawing.Size(544, 240);
			this.txtSQLGeneral.TabIndex = 13;
			this.txtSQLGeneral.Text = "";
			this.txtSQLGeneral.Visible = false;
			// 
			// chkAccount
			// 
			this.chkAccount.Location = new System.Drawing.Point(160, 232);
			this.chkAccount.Name = "chkAccount";
			this.chkAccount.Size = new System.Drawing.Size(168, 16);
			this.chkAccount.TabIndex = 12;
			this.chkAccount.Text = "Account locked";
			// 
			// chkPwd
			// 
			this.chkPwd.Location = new System.Drawing.Point(160, 200);
			this.chkPwd.Name = "chkPwd";
			this.chkPwd.Size = new System.Drawing.Size(168, 16);
			this.chkPwd.TabIndex = 11;
			this.chkPwd.Text = "Password expire";
			// 
			// chkIdentify
			// 
			this.chkIdentify.Location = new System.Drawing.Point(360, 64);
			this.chkIdentify.Name = "chkIdentify";
			this.chkIdentify.Size = new System.Drawing.Size(128, 16);
			this.chkIdentify.TabIndex = 10;
			this.chkIdentify.Text = "Identified externally";
			// 
			// cmbProfile
			// 
			this.cmbProfile.ItemHeight = 13;
			this.cmbProfile.Location = new System.Drawing.Point(160, 163);
			this.cmbProfile.Name = "cmbProfile";
			this.cmbProfile.Size = new System.Drawing.Size(168, 21);
			this.cmbProfile.TabIndex = 4;
			// 
			// cmbTempTablespace
			// 
			this.cmbTempTablespace.ItemHeight = 13;
			this.cmbTempTablespace.Location = new System.Drawing.Point(160, 128);
			this.cmbTempTablespace.Name = "cmbTempTablespace";
			this.cmbTempTablespace.Size = new System.Drawing.Size(168, 21);
			this.cmbTempTablespace.TabIndex = 3;
			// 
			// cmbTablespace
			// 
			this.cmbTablespace.ItemHeight = 13;
			this.cmbTablespace.Location = new System.Drawing.Point(160, 93);
			this.cmbTablespace.Name = "cmbTablespace";
			this.cmbTablespace.Size = new System.Drawing.Size(168, 21);
			this.cmbTablespace.TabIndex = 2;
			// 
			// txtPwd
			// 
			this.txtPwd.Location = new System.Drawing.Point(160, 59);
			this.txtPwd.Name = "txtPwd";
			this.txtPwd.PasswordChar = '*';
			this.txtPwd.Size = new System.Drawing.Size(168, 20);
			this.txtPwd.TabIndex = 1;
			this.txtPwd.Text = "";
			// 
			// txtName
			// 
			this.txtName.Enabled = false;
			this.txtName.Location = new System.Drawing.Point(160, 24);
			this.txtName.Name = "txtName";
			this.txtName.Size = new System.Drawing.Size(168, 20);
			this.txtName.TabIndex = 0;
			this.txtName.Text = "CRMS";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(40, 125);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(128, 24);
			this.label4.TabIndex = 8;
			this.label4.Text = "Temporary tablespace :";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(40, 90);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(128, 24);
			this.label3.TabIndex = 7;
			this.label3.Text = "Default tablespace :";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(40, 55);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(128, 24);
			this.label2.TabIndex = 6;
			this.label2.Text = "Password :";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(40, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(128, 20);
			this.label1.TabIndex = 5;
			this.label1.Text = "Name :";
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(40, 160);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(128, 24);
			this.label5.TabIndex = 9;
			this.label5.Text = "Profile :";
			// 
			// tabObj
			// 
			this.tabObj.Controls.Add(this.dtgridConnHist);
			this.tabObj.Location = new System.Drawing.Point(4, 22);
			this.tabObj.Name = "tabObj";
			this.tabObj.Size = new System.Drawing.Size(584, 270);
			this.tabObj.TabIndex = 1;
			this.tabObj.Text = "Object privileges";
			
			// 
			// dtgridConnHist
			// 
			this.dtgridConnHist.DataMember = "";
			this.dtgridConnHist.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.dtgridConnHist.Location = new System.Drawing.Point(24, 16);
			this.dtgridConnHist.Name = "dtgridConnHist";
			this.dtgridConnHist.Size = new System.Drawing.Size(536, 240);
			this.dtgridConnHist.TabIndex = 0;
			// 
			// groupBox1
			// 
			this.groupBox1.Location = new System.Drawing.Point(40, 64);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(664, 392);
			this.groupBox1.TabIndex = 6;
			this.groupBox1.TabStop = false;
			// 
			// user
			// 
			
			
			this.UserTab.ResumeLayout(false);
			this.tabGeneral.ResumeLayout(false);
			this.tabObj.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.dtgridConnHist)).EndInit();
			this.ResumeLayout(false);

		}
		#endregion

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

		private void checkBox1_CheckedChanged(object sender, System.EventArgs e)
		{
			if(this.chkViewSQL.Checked)
			{
				this.txtSQLGeneral.Visible=true;
			}
			else 
			{
				this.txtSQLGeneral.Visible=false;
			}
		}

		

		// Create DataTable A
		private DataTable CreateDataTableA()
		{
			DataTable aTable = new DataTable("A");
			DataColumn dtCol;
			DataRow dtRow; 
			// Create User column and add to the DataTable.
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Object";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Object";
			dtCol.ReadOnly = true;
			dtCol.Unique = false;

			// Add the column to the DataColumnCollection.
			aTable.Columns.Add(dtCol);

			// Create Select column and add to the table
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Select";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Select";
			dtCol.ReadOnly = true;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			// Create Insert column and add to the table
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Insert";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Insert";
			dtCol.ReadOnly = true;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);
			
			// Create Update column and add to the table
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Update";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Update";
			dtCol.ReadOnly = true;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			// Create Delete column and add to the table
			dtCol = new DataColumn();
			dtCol.DataType= System.Type.GetType("System.String");
			dtCol.ColumnName = "Delete";
			dtCol.AutoIncrement = false;
			dtCol.Caption = "Delete";
			dtCol.ReadOnly = true;
			dtCol.Unique = false;
			aTable.Columns.Add(dtCol);

			
			// Create three rows to the table
			dtRow = aTable.NewRow();
			dtRow["Object"] = "agent";
			dtRow["Select"] = "";
			aTable.Rows.Add(dtRow);

			dtRow = aTable.NewRow();
			dtRow["Object"] = "agent_1";
			dtRow["Select"] = "";
			aTable.Rows.Add(dtRow);

			
			return aTable;
		}
	}
	//public delegate void ComboValueChanged( int changingRow, object newValue );


}
