/********************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using DBManager.forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for FindForm.
	/// </summary>
/********************************************************************************/
	public class MyFindPanel : System.Windows.Forms.Panel
	{
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Panel panFind;
		private System.Windows.Forms.Button btnFind;
		private System.Windows.Forms.Label label1;
		public System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.Panel panFindIn;
		private System.Windows.Forms.Panel panFiller1;
		private System.Windows.Forms.Panel panel1;
		private System.Windows.Forms.GroupBox gbFindIn;
		private System.Windows.Forms.Panel panFiller3;
		private System.Windows.Forms.Panel panFiller4;
		private System.Windows.Forms.Panel panFiller5;
		private System.Windows.Forms.Panel panFiller6;
		private System.Windows.Forms.ListView lvResults;
		private System.Windows.Forms.ColumnHeader ObjectName;
		private System.Windows.Forms.ColumnHeader Type;
		private System.Windows.Forms.ColumnHeader CreatedBy;
		private System.Windows.Forms.ColumnHeader CreatedOn;
		private System.Windows.Forms.CheckBox chkViews;
		private System.Windows.Forms.CheckBox chkTriggers;
		private System.Windows.Forms.CheckBox chkProcedures;
		private System.Windows.Forms.CheckBox chkFunctions;
		private System.Windows.Forms.CheckBox chkViewCode;
		private System.Windows.Forms.CheckBox chkTriggerCode;
		private System.Windows.Forms.CheckBox chkProceduresCode;
		private System.Windows.Forms.CheckBox chkFunctionCode;
		private System.Windows.Forms.CheckBox chkUsers;
		private System.Windows.Forms.CheckBox chkTables;
		private System.Windows.Forms.CheckBox chkSequences;
		private System.Windows.Forms.CheckBox chkDBLinks;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
/********************************************************************************/
		public string schema_name;
		public Database database;
		public string searchIn;
		public TabPage myTabPage;
		private ImageList imgList;

		private string searchStr;
		private const int NOT_FOUND = 0;
		private const int FOUND = 1;
		private const int DO_NOT_SEARCH = 2;
/********************************************************************************/
		public MyFindPanel(Database database, string schema, string FindIn, ImageList imgList)
		{
			// Required for Windows Form Designer support
			InitializeComponent();

			this.database = database;
			this.schema_name = schema;
			this.searchIn = FindIn;
			
			init_tab_indexes();

			if(searchIn.Equals("ALL"))
			{
				this.chkDBLinks.Checked = true;
				this.chkFunctionCode.Checked = true;
				this.chkFunctions.Checked = true;
				this.chkProcedures.Checked = true;
				this.chkProceduresCode.Checked = true;
				this.chkSequences.Checked = true;
				this.chkTables.Checked = true;
				this.chkTriggerCode.Checked = true;
				this.chkTriggers.Checked = true;
				this.chkUsers.Checked = true;
				this.chkViewCode.Checked = true;
				this.chkViews.Checked = true;
			}
			else
			{
				this.chkDBLinks.Checked = searchIn.Equals("DBLINK");
				this.chkFunctions.Checked = searchIn.Equals("FUNCTION") ? true : false;
				this.chkFunctionCode.Checked = searchIn.Equals("FUNCTION_CODE") ? true : false;
				this.chkProcedures.Checked = searchIn.Equals("PROCEDURE") ? true : false;
				this.chkProceduresCode.Checked = searchIn.Equals("PROCEDURE_CODE") ? true : false;
				this.chkSequences.Checked = searchIn.Equals("SEQUENCE") ? true : false;
				this.chkTables.Checked = searchIn.Equals("TABLE") ? true : false;
				this.chkTriggers.Checked = searchIn.Equals("TRIGGER") ? true : false;
				this.chkTriggerCode.Checked = searchIn.Equals("TRIGGER_CODE") ? true : false;
				this.chkUsers.Checked = searchIn.Equals("USER") ? true : false;
				this.chkViews.Checked = searchIn.Equals("VIEW") ? true : false;
				this.chkViewCode.Checked = searchIn.Equals("VIEW_CODE") ? true : false;
			}
			this.btnFind.Click += new EventHandler(btnFind_Click);
			this.Dock = DockStyle.Fill;
			
			this.imgList = imgList;
			this.lvResults.SmallImageList = imgList;
			this.lvResults.StateImageList = imgList;

			this.textBox1.Focus();
			this.textBox1.Select();
		}
/********************************************************************************/
		private void init_tab_indexes()
		{
			this.textBox1.TabIndex = 1;
			this.btnFind.TabIndex = 2;

			this.chkDBLinks.TabIndex = 2;
			this.chkFunctions.TabIndex = 3;
			this.chkFunctionCode.TabIndex = 4;
			
			this.chkSequences.TabIndex = 5;
			this.chkProcedures.TabIndex = 6;
			this.chkProceduresCode.TabIndex = 7;
			
			this.chkTables.TabIndex = 8;
			this.chkTriggers.TabIndex = 9;
			this.chkTriggerCode.TabIndex = 10;

			this.chkUsers.TabIndex = 11;
			this.chkViews.TabIndex = 12;
			this.chkViewCode.TabIndex = 13;

			this.lvResults.TabIndex = 14;
		}
/********************************************************************************/
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
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
/********************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.lvResults = new System.Windows.Forms.ListView();
			this.ObjectName = new System.Windows.Forms.ColumnHeader();
			this.Type = new System.Windows.Forms.ColumnHeader();
			this.CreatedBy = new System.Windows.Forms.ColumnHeader();
			this.CreatedOn = new System.Windows.Forms.ColumnHeader();
			this.panFiller6 = new System.Windows.Forms.Panel();
			this.panFiller5 = new System.Windows.Forms.Panel();
			this.panFiller4 = new System.Windows.Forms.Panel();
			this.panFiller3 = new System.Windows.Forms.Panel();
			this.panFindIn = new System.Windows.Forms.Panel();
			this.gbFindIn = new System.Windows.Forms.GroupBox();
			this.panel1 = new System.Windows.Forms.Panel();
			this.panFiller1 = new System.Windows.Forms.Panel();
			this.panFind = new System.Windows.Forms.Panel();
			this.btnFind = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.label2 = new System.Windows.Forms.Label();
			this.chkViews = new System.Windows.Forms.CheckBox();
			this.chkTriggers = new System.Windows.Forms.CheckBox();
			this.chkProcedures = new System.Windows.Forms.CheckBox();
			this.chkFunctions = new System.Windows.Forms.CheckBox();
			this.chkViewCode = new System.Windows.Forms.CheckBox();
			this.chkTriggerCode = new System.Windows.Forms.CheckBox();
			this.chkProceduresCode = new System.Windows.Forms.CheckBox();
			this.chkFunctionCode = new System.Windows.Forms.CheckBox();
			this.chkUsers = new System.Windows.Forms.CheckBox();
			this.chkTables = new System.Windows.Forms.CheckBox();
			this.chkSequences = new System.Windows.Forms.CheckBox();
			this.chkDBLinks = new System.Windows.Forms.CheckBox();
			
			this.panFindIn.SuspendLayout();
			this.gbFindIn.SuspendLayout();
			this.panFind.SuspendLayout();
			this.SuspendLayout();
			// 
			// FindPanel
			// 
			this.BackColor = System.Drawing.SystemColors.Control;
			this.Controls.Add(this.lvResults);
			this.Controls.Add(this.panFiller6);
			this.Controls.Add(this.panFiller5);
			this.Controls.Add(this.panFiller4);
			this.Controls.Add(this.panFiller3);
			this.Controls.Add(this.panFindIn);
			this.Controls.Add(this.panFind);
			this.Controls.Add(this.label2);
			this.Dock = System.Windows.Forms.DockStyle.Fill;
			this.Location = new System.Drawing.Point(0, 0);
			this.Name = "FindPanel";
			this.Size = new System.Drawing.Size(648, 541);
			this.TabIndex = 0;
			// 
			// lvResults
			// 
			this.lvResults.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
																						this.ObjectName,
																						this.Type,
																						this.CreatedBy,
																						this.CreatedOn});
			this.lvResults.Dock = System.Windows.Forms.DockStyle.Fill;
			this.lvResults.GridLines = true;
			this.lvResults.Location = new System.Drawing.Point(96, 312);
			this.lvResults.Name = "lvResults";
			this.lvResults.Size = new System.Drawing.Size(456, 173);
			this.lvResults.TabIndex = 13;
			this.lvResults.View = System.Windows.Forms.View.Details;
			// 
			// ObjectName
			// 
			this.ObjectName.Text = "Object Name";
			this.ObjectName.Width = 136;
			// 
			// Type
			// 
			this.Type.Text = "Object Type";
			this.Type.Width = 86;
			// 
			// CreatedBy
			// 
			this.CreatedBy.Text = "Created By";
			this.CreatedBy.Width = 89;
			// 
			// CreatedOn
			// 
			this.CreatedOn.Text = "Created On";
			this.CreatedOn.Width = 141;
			// 
			// panFiller6
			// 
			this.panFiller6.BackColor = System.Drawing.SystemColors.Control;
			this.panFiller6.Dock = System.Windows.Forms.DockStyle.Top;
			this.panFiller6.Location = new System.Drawing.Point(96, 296);
			this.panFiller6.Name = "panFiller6";
			this.panFiller6.Size = new System.Drawing.Size(456, 16);
			this.panFiller6.TabIndex = 12;
			// 
			// panFiller5
			// 
			this.panFiller5.BackColor = System.Drawing.SystemColors.Control;
			this.panFiller5.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.panFiller5.Location = new System.Drawing.Point(96, 485);
			this.panFiller5.Name = "panFiller5";
			this.panFiller5.Size = new System.Drawing.Size(456, 56);
			this.panFiller5.TabIndex = 11;
			// 
			// panFiller4
			// 
			this.panFiller4.BackColor = System.Drawing.SystemColors.Control;
			this.panFiller4.Dock = System.Windows.Forms.DockStyle.Right;
			this.panFiller4.Location = new System.Drawing.Point(552, 296);
			this.panFiller4.Name = "panFiller4";
			this.panFiller4.Size = new System.Drawing.Size(96, 245);
			this.panFiller4.TabIndex = 10;
			// 
			// panFiller3
			// 
			this.panFiller3.BackColor = System.Drawing.SystemColors.Control;
			this.panFiller3.Dock = System.Windows.Forms.DockStyle.Left;
			this.panFiller3.Location = new System.Drawing.Point(0, 296);
			this.panFiller3.Name = "panFiller3";
			this.panFiller3.Size = new System.Drawing.Size(96, 245);
			this.panFiller3.TabIndex = 9;
			// 
			// panFindIn
			// 
			this.panFindIn.BackColor = System.Drawing.SystemColors.Control;
			this.panFindIn.Controls.Add(this.gbFindIn);
			this.panFindIn.Controls.Add(this.panel1);
			this.panFindIn.Controls.Add(this.panFiller1);
			this.panFindIn.Dock = System.Windows.Forms.DockStyle.Top;
			this.panFindIn.Location = new System.Drawing.Point(0, 104);
			this.panFindIn.Name = "panFindIn";
			this.panFindIn.Size = new System.Drawing.Size(648, 192);
			this.panFindIn.TabIndex = 8;
			// 
			// gbFindIn
			// 
			this.gbFindIn.Controls.Add(this.chkUsers);
			this.gbFindIn.Controls.Add(this.chkTables);
			this.gbFindIn.Controls.Add(this.chkSequences);
			this.gbFindIn.Controls.Add(this.chkDBLinks);
			this.gbFindIn.Controls.Add(this.chkViewCode);
			this.gbFindIn.Controls.Add(this.chkTriggerCode);
			this.gbFindIn.Controls.Add(this.chkProceduresCode);
			this.gbFindIn.Controls.Add(this.chkFunctionCode);
			this.gbFindIn.Controls.Add(this.chkViews);
			this.gbFindIn.Controls.Add(this.chkTriggers);
			this.gbFindIn.Controls.Add(this.chkProcedures);
			this.gbFindIn.Controls.Add(this.chkFunctions);
			this.gbFindIn.Dock = System.Windows.Forms.DockStyle.Fill;
			this.gbFindIn.Location = new System.Drawing.Point(96, 0);
			this.gbFindIn.Name = "gbFindIn";
			this.gbFindIn.Size = new System.Drawing.Size(456, 192);
			this.gbFindIn.TabIndex = 2;
			this.gbFindIn.TabStop = false;
			this.gbFindIn.Text = "Find In";
			// 
			// panel1
			// 
			this.panel1.BackColor = System.Drawing.SystemColors.Control;
			this.panel1.Dock = System.Windows.Forms.DockStyle.Right;
			this.panel1.Location = new System.Drawing.Point(552, 0);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(96, 192);
			this.panel1.TabIndex = 1;
			// 
			// panFiller1
			// 
			this.panFiller1.BackColor = System.Drawing.SystemColors.Control;
			this.panFiller1.Dock = System.Windows.Forms.DockStyle.Left;
			this.panFiller1.Location = new System.Drawing.Point(0, 0);
			this.panFiller1.Name = "panFiller1";
			this.panFiller1.Size = new System.Drawing.Size(96, 192);
			this.panFiller1.TabIndex = 0;
			// 
			// panFind
			// 
			this.panFind.BackColor = System.Drawing.SystemColors.Control;
			this.panFind.Controls.Add(this.btnFind);
			this.panFind.Controls.Add(this.label1);
			this.panFind.Controls.Add(this.textBox1);
			this.panFind.Dock = System.Windows.Forms.DockStyle.Top;
			this.panFind.Location = new System.Drawing.Point(0, 40);
			this.panFind.Name = "panFind";
			this.panFind.Size = new System.Drawing.Size(648, 64);
			this.panFind.TabIndex = 7;
			// 
			// btnFind
			// 
			this.btnFind.BackColor = System.Drawing.SystemColors.Control;
			this.btnFind.Location = new System.Drawing.Point(472, 24);
			this.btnFind.Name = "btnFind";
			this.btnFind.Size = new System.Drawing.Size(72, 24);
			this.btnFind.TabIndex = 13;
			this.btnFind.Text = "Find";
			// 
			// label1
			// 
			this.label1.BackColor = System.Drawing.SystemColors.Control;
			this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label1.Location = new System.Drawing.Point(104, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(80, 20);
			this.label1.TabIndex = 12;
			this.label1.Text = "Text To Find :";
			// 
			// textBox1
			// 
			this.textBox1.BackColor = System.Drawing.Color.White;
			this.textBox1.Location = new System.Drawing.Point(200, 24);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(256, 20);
			this.textBox1.TabIndex = 11;
			this.textBox1.Text = "";
			// 
			// label2
			// 
			this.label2.BackColor = System.Drawing.SystemColors.Control;
			this.label2.Dock = System.Windows.Forms.DockStyle.Top;
			this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label2.Location = new System.Drawing.Point(0, 0);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(648, 40);
			this.label2.TabIndex = 6;
			this.label2.Text = "FIND";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// chkViews
			// 
			this.chkViews.Location = new System.Drawing.Point(176, 152);
			this.chkViews.Name = "chkViews";
			this.chkViews.Size = new System.Drawing.Size(112, 24);
			this.chkViews.TabIndex = 7;
			this.chkViews.Text = "Views";
			// 
			// chkTriggers
			// 
			this.chkTriggers.Location = new System.Drawing.Point(176, 112);
			this.chkTriggers.Name = "chkTriggers";
			this.chkTriggers.Size = new System.Drawing.Size(112, 24);
			this.chkTriggers.TabIndex = 5;
			this.chkTriggers.Text = "Triggers";
			// 
			// chkProcedures
			// 
			this.chkProcedures.Location = new System.Drawing.Point(176, 72);
			this.chkProcedures.Name = "chkProcedures";
			this.chkProcedures.Size = new System.Drawing.Size(112, 24);
			this.chkProcedures.TabIndex = 2;
			this.chkProcedures.Text = "Procedures";
			// 
			// chkFunctions
			// 
			this.chkFunctions.Location = new System.Drawing.Point(176, 32);
			this.chkFunctions.Name = "chkFunctions";
			this.chkFunctions.Size = new System.Drawing.Size(112, 24);
			this.chkFunctions.TabIndex = 1;
			this.chkFunctions.Text = "Functions";
			// 
			// chkViewCode
			// 
			this.chkViewCode.Location = new System.Drawing.Point(320, 152);
			this.chkViewCode.Name = "chkViewCode";
			this.chkViewCode.Size = new System.Drawing.Size(112, 24);
			this.chkViewCode.TabIndex = 11;
			this.chkViewCode.Text = "View Code";
			// 
			// chkTriggerCode
			// 
			this.chkTriggerCode.Location = new System.Drawing.Point(320, 112);
			this.chkTriggerCode.Name = "chkTriggerCode";
			this.chkTriggerCode.Size = new System.Drawing.Size(112, 24);
			this.chkTriggerCode.TabIndex = 10;
			this.chkTriggerCode.Text = "Trigger Code";
			// 
			// chkProceduresCode
			// 
			this.chkProceduresCode.Location = new System.Drawing.Point(320, 72);
			this.chkProceduresCode.Name = "chkProceduresCode";
			this.chkProceduresCode.Size = new System.Drawing.Size(112, 24);
			this.chkProceduresCode.TabIndex = 9;
			this.chkProceduresCode.Text = "Procedure Code";
			// 
			// chkFunctionCode
			// 
			this.chkFunctionCode.Location = new System.Drawing.Point(320, 32);
			this.chkFunctionCode.Name = "chkFunctionCode";
			this.chkFunctionCode.Size = new System.Drawing.Size(112, 24);
			this.chkFunctionCode.TabIndex = 8;
			this.chkFunctionCode.Text = "Function Code";
			// 
			// chkUsers
			// 
			this.chkUsers.Location = new System.Drawing.Point(32, 152);
			this.chkUsers.Name = "chkUsers";
			this.chkUsers.Size = new System.Drawing.Size(112, 24);
			this.chkUsers.TabIndex = 15;
			this.chkUsers.Text = "Users";
			// 
			// chkTables
			// 
			this.chkTables.Location = new System.Drawing.Point(32, 112);
			this.chkTables.Name = "chkTables";
			this.chkTables.Size = new System.Drawing.Size(112, 24);
			this.chkTables.TabIndex = 14;
			this.chkTables.Text = "Tables";
			// 
			// chkSequences
			// 
			this.chkSequences.Location = new System.Drawing.Point(32, 72);
			this.chkSequences.Name = "chkSequences";
			this.chkSequences.Size = new System.Drawing.Size(112, 24);
			this.chkSequences.TabIndex = 13;
			this.chkSequences.Text = "Sequences";
			// 
			// chkDBLinks
			// 
			this.chkDBLinks.Location = new System.Drawing.Point(32, 32);
			this.chkDBLinks.Name = "chkDBLinks";
			this.chkDBLinks.Size = new System.Drawing.Size(112, 24);
			this.chkDBLinks.TabIndex = 12;
			this.chkDBLinks.Text = "DB Links";
			
            			
			this.panFindIn.ResumeLayout(false);
			this.gbFindIn.ResumeLayout(false);
			this.panFind.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/********************************************************************************/
		private void btnFind_Click(object sender, System.EventArgs e)
		{
			if (!this.chkDBLinks.Checked && !this.chkFunctionCode.Checked &&
				!this.chkFunctions.Checked &&
				!this.chkProcedures.Checked &&
				!this.chkProceduresCode.Checked &&
				!this.chkSequences.Checked &&
				!this.chkTables.Checked &&
				!this.chkTriggerCode.Checked &&
				!this.chkTriggers.Checked &&
				!this.chkUsers.Checked &&
				!this.chkViewCode.Checked &&
				!this.chkViews.Checked)
			{
				MessageBox.Show("Select at least one checkbox.", "DB Manager", 
									MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			searchStr = this.textBox1.Text;
			if(searchStr.Trim().Length == 0)
			{
				MessageBox.Show("Enter text to find.", "DB Manager", 
									MessageBoxButtons.OK, MessageBoxIcon.Information);
				return;
			}
			lvResults.Items.Clear();
			string objsNotFound = "";
			/*-------------------------------------------------------------------------*/
			//search DB Links
			int result = this.chkDBLinks.Checked ? searchDBLinks() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "DB Links\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Sequences
			result = (this.chkSequences.Checked)? searchSequences() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Sequences\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Tables
			result = (this.chkTables.Checked)? searchTables() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Tables\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Functions
			result = (this.chkFunctions.Checked || this.chkFunctionCode.Checked)? 
								searchFunctions() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Functions\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Procedures
			result = (this.chkProcedures.Checked || this.chkProceduresCode.Checked)? 
								searchProcedures() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Procedures\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Triggers
			result = (this.chkTriggers.Checked || this.chkTriggerCode.Checked)? 
							searchTriggers() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Triggers\n" : ""; 
			/*-------------------------------------------------------------------------*/
			//search Views
			result = (this.chkViews.Checked || this.chkViewCode.Checked)? 
							searchViews() : DO_NOT_SEARCH;
			objsNotFound += (result == NOT_FOUND) ? "Views\n" : ""; 
			/*-------------------------------------------------------------------------*/
			if (objsNotFound.Length != 0)
			{
				MessageBox.Show("No matching objects found in the following: \n" + objsNotFound, 
									"DB Manager", MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
				objsNotFound = "";
			}
		}
/********************************************************************************/
		private int searchDBLinks()
		{
			int found = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("DBLINK");
			foreach (DBLink d in database.getSchema(schema_name).dblinks.Values)
			{	
				if(d.dblink_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					found = FOUND;
					lvResults.Items.Add(new ListViewItem(new string[] {d.dblink_name,
																		 "DB Link",
																		 d.created_by ,
																		 d.created_on.ToString()}, imgIndex));
				}
			}
			return found;
		}
/********************************************************************************/
		private int searchSequences()
		{
			int found = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("SEQUENCE");
			foreach (Sequence s in database.getSchema(schema_name).sequences.Values)
			{	
				if(s.sequence_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					found = FOUND;
					lvResults.Items.Add(new ListViewItem(new string[] {s.sequence_name,
																		  "Sequence",
																		  s.created_by ,
																		  s.created_on.ToString()}, imgIndex));
				}
			}
			return found;
		}
/********************************************************************************/
		private int searchTables()
		{
			int found = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("TABLE");
			foreach (Table t in database.getSchema(schema_name).tables.Values)
			{	
				if(t.table_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					found = FOUND;
					lvResults.Items.Add(new ListViewItem(new string[] {t.table_name,
																		  "Table",
																		  t.created_by ,
																		  t.created_on.ToString()}, imgIndex));
				}
			}
			return found;
		}
/********************************************************************************/
		private int searchFunctions()
		{
			int result = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("FUNCTION");
			foreach (Function f in database.getSchema(schema_name).functions.Values)
			{	
				if (this.chkFunctions.Checked)
				{
					if(f.function_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (this.chkFunctionCode.Checked)
				{
					if(f.function_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (result == FOUND)
				{
					lvResults.Items.Add(new ListViewItem(new string[] {f.function_name,
																		  "Function",
																		  f.created_by ,
																		  f.created_on.ToString()}, imgIndex));
				}
			}
			return result;
		}
/********************************************************************************/
		private int searchProcedures()
		{
			int result = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("PROCEDURE");
			foreach (Procedure p in database.getSchema(schema_name).procedures.Values)
			{	
				if (this.chkProcedures.Checked)
				{
					if(p.procedure_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (this.chkProceduresCode.Checked)
				{
					if(p.procedure_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (result == FOUND)
				{
					lvResults.Items.Add(new ListViewItem(new string[] {p.procedure_name,
																		  "Procedure",
																		  p.created_by ,
																		  p.created_on.ToString()}, imgIndex));
				}
			}
			return result;
		}
/********************************************************************************/
		private int searchTriggers()
		{
			int result = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("TRIGGER");
			foreach (Trigger t in database.getSchema(schema_name).triggers.Values)
			{	
				if (this.chkTriggers.Checked)
				{
					if(t.trigger_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (this.chkTriggerCode.Checked)
				{
					if(t.trigger_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (result == FOUND)
				{
					lvResults.Items.Add(new ListViewItem(new string[] {t.trigger_name,
																		  "Trigger",
																		  t.created_by ,
																		  t.created_on.ToString()}, imgIndex));
				}
			}
			return result;
		}
/********************************************************************************/
		private int searchViews()
		{
			int result = NOT_FOUND;
			int imgIndex = MyImageList.getImgList().getImageIndex("VIEW");
			foreach (View v in database.getSchema(schema_name).views.Values)
			{	
				if (this.chkViews.Checked)
				{
					if(v.view_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (this.chkViewCode.Checked)
				{
					if(v.view_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
					{
						result = FOUND;
					}
				}
				if (result == FOUND)
				{
					lvResults.Items.Add(new ListViewItem(new string[] {v.view_name,
																		  "View",
																		  v.created_by ,
																		  v.created_on.ToString()}, imgIndex));
				}
			}
			return result;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/