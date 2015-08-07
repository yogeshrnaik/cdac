using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace DBManager.classes
{
	/// <summary>
	/// Summary description for FindPanel.
	/// </summary>
	public class FindPanel : Panel
	{
		
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.CheckBox chkProc;
		private System.Windows.Forms.CheckBox chkFunc;
		private System.Windows.Forms.CheckBox chkTab;
		private System.Windows.Forms.CheckBox chkProcCode;
		private System.Windows.Forms.CheckBox chkFuncCode;
		private System.Windows.Forms.CheckBox chkViews;
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Button btnFind;
		private System.Windows.Forms.ListView FindList;
		private System.Windows.Forms.ColumnHeader creationDate;
		private System.Windows.Forms.ColumnHeader createdBy;
		private System.Windows.Forms.ColumnHeader ListName;
		private System.Windows.Forms.ColumnHeader ListType;
		private System.Windows.Forms.GroupBox groupBox2;
		private System.Windows.Forms.Label label2;
		public string schema_name;
		public Database database;
		public string searchIn;
		public TabPage myTabPage;
		
		private string searchStr;
		private string msgStr="";
		
		
		
		
		public FindPanel(Database database, string schema, string FindIn)
		{
			//
			// TODO: Add constructor logic here
			//


			InitializeComponent();
			this.database = database;
			this.schema_name = schema;
			this.searchIn=FindIn;

			this.chkFunc.Checked=false;
			this.chkTab.Checked=false;
			this.chkProcCode.Checked=false;
			this.chkFuncCode.Checked=false;
			this.chkProc.Checked=false;
			this.chkViews.Checked=false;


			if(searchIn.Equals("TABLE"))
			{
				this.chkTab.Checked=true;
				
			}
			else if(searchIn.Equals("FUNCTION_CODE"))
			{
				
				this.chkFuncCode.Checked=true;
			}
			else if(searchIn.Equals("PROCEDURE_CODE"))
			{
				this.chkProcCode.Checked=true;
			}
			else if(searchIn.Equals("FUNCTION"))
			{
				this.chkFunc.Checked=true;
			}
			else if(searchIn.Equals("PROCEDURE"))
			{
				this.chkProc.Checked=true;
			}
			else if(searchIn.Equals("VIEW"))
			{
				this.chkViews.Checked=true;
			}
			else if(searchIn.Equals("ALL"))
			{
				this.chkTab.Checked=true;
				this.chkFuncCode.Checked=true;
				this.chkProcCode.Checked=true;
				this.chkFunc.Checked=true;
				this.chkProc.Checked=true;
				this.chkViews.Checked=true;
			}

			this.Dock = DockStyle.Fill;
		}


		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			
			
			this.label2 = new System.Windows.Forms.Label();
			this.FindList = new System.Windows.Forms.ListView();
			this.ListName = new System.Windows.Forms.ColumnHeader();
			this.ListType = new System.Windows.Forms.ColumnHeader();
			this.creationDate = new System.Windows.Forms.ColumnHeader();
			this.createdBy = new System.Windows.Forms.ColumnHeader();
			this.btnFind = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.chkViews = new System.Windows.Forms.CheckBox();
			this.chkFuncCode = new System.Windows.Forms.CheckBox();
			this.chkProcCode = new System.Windows.Forms.CheckBox();
			this.chkTab = new System.Windows.Forms.CheckBox();
			this.chkFunc = new System.Windows.Forms.CheckBox();
			this.chkProc = new System.Windows.Forms.CheckBox();
			this.groupBox2 = new System.Windows.Forms.GroupBox();
			
			this.groupBox1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.Controls.Add(this.label2);
			this.Controls.Add(this.FindList);
			this.Controls.Add(this.btnFind);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.textBox1);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.groupBox2);
			
			this.TabIndex = 0;
			// 
			// label2
			// 
			this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label2.Location = new System.Drawing.Point(152, 24);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(352, 40);
			this.label2.TabIndex = 5;
			this.label2.Text = "FIND";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// FindList
			// 
			this.FindList.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
																					   this.ListName,
																					   this.ListType,
																					   this.creationDate,
																					   this.createdBy});
			this.FindList.GridLines = true;
			this.FindList.Sorting = System.Windows.Forms.SortOrder.Ascending;
			this.FindList.Location = new System.Drawing.Point(80, 312);
			this.FindList.Name = "FindList";
			this.FindList.Size = new System.Drawing.Size(512, 240);
			this.FindList.TabIndex = 3;
			this.FindList.View = System.Windows.Forms.View.Details;
			// 
			// ListName
			// 
			this.ListName.Text = "Name";
			this.ListName.Width = 152;
			// 
			// ListType
			// 
			this.ListType.Text = "Type";
			this.ListType.Width = 167;
			// 
			// creationDate
			// 
			this.creationDate.Text = "Created On";
			this.creationDate.Width = 92;
			// 
			// createdBy
			// 
			this.createdBy.Text = "Created By";
			this.createdBy.Width = 96;
			// 
			// btnFind
			// 
			this.btnFind.Location = new System.Drawing.Point(448, 104);
			this.btnFind.Name = "btnFind";
			this.btnFind.Size = new System.Drawing.Size(72, 24);
			this.btnFind.TabIndex = 2;
			this.btnFind.Text = "Find";
			this.btnFind.Click += new System.EventHandler(this.btnFind_Click);
			// 
			// label1
			// 
			this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label1.Location = new System.Drawing.Point(80, 104);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(80, 20);
			this.label1.TabIndex = 2;
			this.label1.Text = "Text To Find :";
			
			// 
			// textBox1
			// 
			this.textBox1.Location = new System.Drawing.Point(176, 104);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(256, 20);
			this.textBox1.TabIndex = 1;
			this.textBox1.Text = "";
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.chkViews);
			this.groupBox1.Controls.Add(this.chkFuncCode);
			this.groupBox1.Controls.Add(this.chkProcCode);
			this.groupBox1.Controls.Add(this.chkTab);
			this.groupBox1.Controls.Add(this.chkFunc);
			this.groupBox1.Controls.Add(this.chkProc);
			this.groupBox1.Location = new System.Drawing.Point(80, 144);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(360, 136);
			this.groupBox1.TabIndex = 0;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "Find In ";
			// 
			// chkViews
			// 
			this.chkViews.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkViews.Location = new System.Drawing.Point(216, 88);
			this.chkViews.Name = "chkViews";
			this.chkViews.Size = new System.Drawing.Size(88, 16);
			this.chkViews.TabIndex = 8;
			this.chkViews.Text = "Views";
			// 
			// chkFuncCode
			// 
			this.chkFuncCode.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkFuncCode.Location = new System.Drawing.Point(216, 56);
			this.chkFuncCode.Name = "chkFuncCode";
			this.chkFuncCode.Size = new System.Drawing.Size(104, 16);
			this.chkFuncCode.TabIndex = 7;
			this.chkFuncCode.Text = "Function Code";
			// 
			// chkProcCode
			// 
			this.chkProcCode.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkProcCode.Location = new System.Drawing.Point(216, 24);
			this.chkProcCode.Name = "chkProcCode";
			this.chkProcCode.Size = new System.Drawing.Size(120, 16);
			this.chkProcCode.TabIndex = 6;
			this.chkProcCode.Text = "Procedure Code";
			// 
			// chkTab
			// 
			this.chkTab.Location = new System.Drawing.Point(80, 88);
			this.chkTab.Name = "chkTab";
			this.chkTab.Size = new System.Drawing.Size(104, 16);
			this.chkTab.TabIndex = 5;
			this.chkTab.Text = "Tables";
			// 
			// chkFunc
			// 
			this.chkFunc.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkFunc.Location = new System.Drawing.Point(80, 56);
			this.chkFunc.Name = "chkFunc";
			this.chkFunc.Size = new System.Drawing.Size(104, 16);
			this.chkFunc.TabIndex = 4;
			this.chkFunc.Text = "Functions";
			// 
			// chkProc
			// 
			this.chkProc.Font = new System.Drawing.Font("Microsoft Sans Serif", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkProc.Location = new System.Drawing.Point(80, 24);
			this.chkProc.Name = "chkProc";
			this.chkProc.Size = new System.Drawing.Size(120, 16);
			this.chkProc.TabIndex = 3;
			this.chkProc.Text = "Procedures";
			// 
			// groupBox2
			// 
			this.groupBox2.Location = new System.Drawing.Point(48, 72);
			this.groupBox2.Name = "groupBox2";
			this.groupBox2.Size = new System.Drawing.Size(576, 512);
			this.groupBox2.TabIndex = 4;
			this.groupBox2.TabStop = false;
			
			// 
			// search
			// 
			
			
			this.ResumeLayout(false);
			this.groupBox1.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion

		

		private void btnFind_Click(object sender, System.EventArgs e)
		{	searchStr=this.textBox1.Text;

			if(searchStr.Length==0) 
			{
				MessageBox.Show("Enter text to find.","DB Manager",MessageBoxButtons.OK,MessageBoxIcon.Information);
				return;
			}
			FindList.Items.Clear();
			msgStr = "";
			if(chkTab.Checked)
			{
				searchTableName();	
			}
			if(chkFuncCode.Checked)
			{
				searchFuncCode();	
			}
			if(chkProcCode.Checked)
			{
				searchProcCode();
			}
			if(chkFunc.Checked)
			{
				searchFuncName();
			}
			if(chkProc.Checked)
			{
				searchProcName();
			}
			if(chkViews.Checked)
			{
				searchViewName();
			}
			if (msgStr.Length != 0)
			{
				MessageBox.Show("No Matching " + msgStr + "found.", "DB Manager", 
									MessageBoxButtons.OK,MessageBoxIcon.Information);
			}
		}

		private void searchTableName()
		{	int i=0;
			foreach (Table t in database.getSchema(schema_name).tables.Values)
			{	
				if(t.table_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					
					FindList.Items.Add(new ListViewItem(new string[] {t.table_name,
													  "Table",
													  t.created_by,
													  t.created_on.ToString()}, -1));
					

				}
			}

			if(i==0)
			{
				msgStr+=" Tables ";

			}
				

				
			

		}

		private void searchFuncName()
		{

			int i=0;
			foreach (Function f in database.getSchema(schema_name).functions.Values)
			{	
				if(f.function_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					
					FindList.Items.Add(new ListViewItem(new string[] {f.function_name,
																		 "Function",
																		 f.created_by ,
																		 f.created_on.ToString()}, -1));
					

				}
			}

			if(i==0)
			{
				msgStr+=" Functions ";

			}
				
		}

		private void searchProcName(){
			int i=0;
			foreach (Procedure p in database.getSchema(schema_name).procedures.Values)
			{	
				if(p.procedure_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					
					FindList.Items.Add(new ListViewItem(new string[] {p.procedure_name,
																		 "Procedure",
																		 p.created_by ,
																		 p.created_on.ToString()}, -1));
					

				}
			}

			if(i==0)
			{
				msgStr+=" Procedures ";

			}
		}

		private void searchProcCode(){
			int i=0;
			foreach (Procedure p in database.getSchema(schema_name).procedures.Values)
			{	
				if(p.procedure_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					
					FindList.Items.Add(new ListViewItem(new string[] {p.procedure_name,
																		 "Procedure",
																		 p.created_by ,
																		 p.created_on.ToString()}, -1));
					

				}
			}

			if(i==0)
			{
				msgStr+=" Procedure Code ";

			}
		}

		private void searchFuncCode(){
			int i=0;
			foreach (Function f in database.getSchema(schema_name).functions.Values)
			{	
				if(f.function_sql.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					
					FindList.Items.Add(new ListViewItem(new string[] {f.function_name,
																		 "Function",
																		 f.created_by ,
																		 f.created_on.ToString()}, -1));
					

				}
			}

			if(i==0)
			{
				msgStr+=" Functions Code ";

			}
				
		}

		private void searchViewName()
		{
			int i=0;
			foreach (View v in database.getSchema(schema_name).views.Values)
			{	
				if(v.view_name.ToUpper().IndexOf(searchStr.ToUpper()) >= 0)
				{
					i=1;
					FindList.Items.Add(new ListViewItem(new string[] {v.view_name,
																		 "View",
																		 v.created_by ,
																		 v.created_on.ToString()}, -1));
				}
			}
			if(i==0)
			{
				msgStr += " Views ";
			}
		}

	}
}
