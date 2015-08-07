using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace DBManager
{
	/// <summary>
	/// Summary description for MDIForm2.
	/// </summary>
	public class MDIForm2 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.ToolBar toolBar1;
		private System.Windows.Forms.StatusBar statusBar1;
		private System.Windows.Forms.ToolBar toolBar2;
		private System.Windows.Forms.ToolBar toolBar3;
		private System.Windows.Forms.Panel panel1;
		private System.Windows.Forms.Splitter splitter1;
		private System.Windows.Forms.Splitter splitter2;
		private System.Windows.Forms.ListView listView2;
		private System.Windows.Forms.MainMenu mainMenu1;
		private System.Windows.Forms.MenuItem menuItem1;
		private System.Windows.Forms.MenuItem menuItem2;
		private System.Windows.Forms.MenuItem menuItem3;
		private System.Windows.Forms.MenuItem menuItem4;
		private System.Windows.Forms.ListView listView1;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public MDIForm2()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

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

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.toolBar1 = new System.Windows.Forms.ToolBar();
			this.statusBar1 = new System.Windows.Forms.StatusBar();
			this.toolBar2 = new System.Windows.Forms.ToolBar();
			this.toolBar3 = new System.Windows.Forms.ToolBar();
			this.panel1 = new System.Windows.Forms.Panel();
			this.splitter1 = new System.Windows.Forms.Splitter();
			this.splitter2 = new System.Windows.Forms.Splitter();
			this.listView2 = new System.Windows.Forms.ListView();
			this.mainMenu1 = new System.Windows.Forms.MainMenu();
			this.menuItem1 = new System.Windows.Forms.MenuItem();
			this.menuItem2 = new System.Windows.Forms.MenuItem();
			this.menuItem3 = new System.Windows.Forms.MenuItem();
			this.menuItem4 = new System.Windows.Forms.MenuItem();
			this.listView1 = new System.Windows.Forms.ListView();
			this.panel1.SuspendLayout();
			this.SuspendLayout();
			// 
			// toolBar1
			// 
			this.toolBar1.DropDownArrows = true;
			this.toolBar1.Name = "toolBar1";
			this.toolBar1.ShowToolTips = true;
			this.toolBar1.Size = new System.Drawing.Size(952, 39);
			this.toolBar1.TabIndex = 1;
			// 
			// statusBar1
			// 
			this.statusBar1.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.statusBar1.Location = new System.Drawing.Point(0, 515);
			this.statusBar1.Name = "statusBar1";
			this.statusBar1.Size = new System.Drawing.Size(952, 16);
			this.statusBar1.TabIndex = 4;
			this.statusBar1.Text = "This is the Status Bar";
			// 
			// toolBar2
			// 
			this.toolBar2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
			this.toolBar2.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.toolBar2.DropDownArrows = true;
			this.toolBar2.Location = new System.Drawing.Point(0, 474);
			this.toolBar2.Name = "toolBar2";
			this.toolBar2.ShowToolTips = true;
			this.toolBar2.Size = new System.Drawing.Size(952, 41);
			this.toolBar2.TabIndex = 5;
			// 
			// toolBar3
			// 
			this.toolBar3.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.toolBar3.DropDownArrows = true;
			this.toolBar3.Location = new System.Drawing.Point(0, 435);
			this.toolBar3.Name = "toolBar3";
			this.toolBar3.ShowToolTips = true;
			this.toolBar3.Size = new System.Drawing.Size(952, 39);
			this.toolBar3.TabIndex = 6;
			// 
			// panel1
			// 
			this.panel1.Controls.AddRange(new System.Windows.Forms.Control[] {
																																				 this.listView2,
																																				 this.splitter2,
																																				 this.listView1});
			this.panel1.Dock = System.Windows.Forms.DockStyle.Left;
			this.panel1.Location = new System.Drawing.Point(0, 39);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(200, 396);
			this.panel1.TabIndex = 9;
			// 
			// splitter1
			// 
			this.splitter1.Location = new System.Drawing.Point(200, 39);
			this.splitter1.Name = "splitter1";
			this.splitter1.Size = new System.Drawing.Size(8, 396);
			this.splitter1.TabIndex = 10;
			this.splitter1.TabStop = false;
			// 
			// splitter2
			// 
			this.splitter2.Dock = System.Windows.Forms.DockStyle.Top;
			this.splitter2.Location = new System.Drawing.Point(0, 228);
			this.splitter2.Name = "splitter2";
			this.splitter2.Size = new System.Drawing.Size(200, 8);
			this.splitter2.TabIndex = 2;
			this.splitter2.TabStop = false;
			// 
			// listView2
			// 
			this.listView2.Dock = System.Windows.Forms.DockStyle.Fill;
			this.listView2.Location = new System.Drawing.Point(0, 236);
			this.listView2.Name = "listView2";
			this.listView2.Size = new System.Drawing.Size(200, 160);
			this.listView2.TabIndex = 3;
			// 
			// mainMenu1
			// 
			this.mainMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																							this.menuItem1,
																																							this.menuItem2});
			// 
			// menuItem1
			// 
			this.menuItem1.Index = 0;
			this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																							this.menuItem4,
																																							this.menuItem3});
			this.menuItem1.Text = "File";
			// 
			// menuItem2
			// 
			this.menuItem2.Index = 1;
			this.menuItem2.Text = "Help";
			// 
			// menuItem3
			// 
			this.menuItem3.Index = 1;
			this.menuItem3.Text = "Exit";
			this.menuItem3.Click += new System.EventHandler(this.menuItem3_Click);
			// 
			// menuItem4
			// 
			this.menuItem4.Index = 0;
			this.menuItem4.Text = "New";
			this.menuItem4.Click += new System.EventHandler(this.menuItem4_Click);
			// 
			// listView1
			// 
			this.listView1.Dock = System.Windows.Forms.DockStyle.Top;
			this.listView1.Name = "listView1";
			this.listView1.Size = new System.Drawing.Size(200, 228);
			this.listView1.TabIndex = 1;
			// 
			// MDIForm2
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(952, 531);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																																	this.splitter1,
																																	this.panel1,
																																	this.toolBar3,
																																	this.toolBar2,
																																	this.statusBar1,
																																	this.toolBar1});
			this.IsMdiContainer = true;
			this.Menu = this.mainMenu1;
			this.Name = "MDIForm2";
			this.Text = "MDIForm2";
			this.Load += new System.EventHandler(this.MDIForm2_Load);
			this.panel1.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion

		private void MDIForm2_Load(object sender, System.EventArgs e)
		{
			toolBar1.Dock = DockStyle.Top;
			this.statusBar1.Dock = DockStyle.Bottom;
			toolBar2.Dock = DockStyle.Bottom;
			
		}
		static void Main() 
		{
			Application.Run(new MDIForm2());
		}

		private void menuItem3_Click(object sender, System.EventArgs e)
		{
			Application.Exit();
		}

		private void menuItem4_Click(object sender, System.EventArgs e)
		{
			int FormCount = 0;
			Form1 frmTemp=new Form1();
			frmTemp.MdiParent=this;
			frmTemp.Text="Window#" + FormCount.ToString();
			FormCount++;
			frmTemp.Show();
		}
	}
}
