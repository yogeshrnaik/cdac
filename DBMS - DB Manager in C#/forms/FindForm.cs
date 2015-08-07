/********************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.forms
{
	/// <summary>
	/// Summary description for FindForm.
	/// </summary>
/********************************************************************************/
	public class FindForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Panel FindPanel;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Panel panFind;
		private System.Windows.Forms.Button btnFind;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox textBox1;
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
		public FindForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
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
			this.FindPanel = new System.Windows.Forms.Panel();
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
			this.FindPanel.SuspendLayout();
			this.panFindIn.SuspendLayout();
			this.gbFindIn.SuspendLayout();
			this.panFind.SuspendLayout();
			this.SuspendLayout();
			// 
			// FindPanel
			// 
			this.FindPanel.BackColor = System.Drawing.SystemColors.Control;
			this.FindPanel.Controls.Add(this.lvResults);
			this.FindPanel.Controls.Add(this.panFiller6);
			this.FindPanel.Controls.Add(this.panFiller5);
			this.FindPanel.Controls.Add(this.panFiller4);
			this.FindPanel.Controls.Add(this.panFiller3);
			this.FindPanel.Controls.Add(this.panFindIn);
			this.FindPanel.Controls.Add(this.panFind);
			this.FindPanel.Controls.Add(this.label2);
			this.FindPanel.Dock = System.Windows.Forms.DockStyle.Fill;
			this.FindPanel.Location = new System.Drawing.Point(0, 0);
			this.FindPanel.Name = "FindPanel";
			this.FindPanel.Size = new System.Drawing.Size(648, 541);
			this.FindPanel.TabIndex = 0;
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
			// 
			// FindForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(648, 541);
			this.Controls.Add(this.FindPanel);
			this.Name = "FindForm";
			this.Text = "FindForm";
			this.FindPanel.ResumeLayout(false);
			this.panFindIn.ResumeLayout(false);
			this.gbFindIn.ResumeLayout(false);
			this.panFind.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/