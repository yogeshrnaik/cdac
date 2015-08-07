using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace DBManager
{
	/// <summary>
	/// Summary description for frmAutoConn.
	/// </summary>
	public class frmAutoConn : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.ListView lvAutoConn;
		private System.Windows.Forms.ColumnHeader chDb;
		private System.Windows.Forms.ColumnHeader chUser;
		private System.Windows.Forms.ColumnHeader chStatus;
		private System.ComponentModel.IContainer components;
		private System.Windows.Forms.Timer tmrAutoConn;
		static int counter = 0;
		public frmAutoConn()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			counter = 0;
			this.tmrAutoConn.Interval = 2000;
			this.tmrAutoConn.Start();
			
			

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
			this.components = new System.ComponentModel.Container();
			System.Windows.Forms.ListViewItem listViewItem1 = new System.Windows.Forms.ListViewItem(new string[] {
																													 "Database1",
																													 "User1",
																													 "Connecting..."}, -1);
			System.Windows.Forms.ListViewItem listViewItem2 = new System.Windows.Forms.ListViewItem(new string[] {
																													 "Database2",
																													 "User2",
																													 ""}, -1);
			System.Windows.Forms.ListViewItem listViewItem3 = new System.Windows.Forms.ListViewItem(new string[] {
																													 "Database3",
																													 "User3",
																													 ""}, -1);
			this.button1 = new System.Windows.Forms.Button();
			this.lvAutoConn = new System.Windows.Forms.ListView();
			this.chDb = new System.Windows.Forms.ColumnHeader();
			this.chUser = new System.Windows.Forms.ColumnHeader();
			this.chStatus = new System.Windows.Forms.ColumnHeader();
			this.tmrAutoConn = new System.Windows.Forms.Timer(this.components);
			this.SuspendLayout();
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(152, 280);
			this.button1.Name = "button1";
			this.button1.TabIndex = 1;
			this.button1.Text = "Cancel";
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// lvAutoConn
			// 
			this.lvAutoConn.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
																						 this.chDb,
																						 this.chUser,
																						 this.chStatus});
			this.lvAutoConn.GridLines = true;
			listViewItem2.Tag = "";
			this.lvAutoConn.Items.AddRange(new System.Windows.Forms.ListViewItem[] {
																					   listViewItem1,
																					   listViewItem2,
																					   listViewItem3});
			this.lvAutoConn.Location = new System.Drawing.Point(8, 16);
			this.lvAutoConn.Name = "lvAutoConn";
			this.lvAutoConn.Size = new System.Drawing.Size(336, 248);
			this.lvAutoConn.TabIndex = 2;
			this.lvAutoConn.View = System.Windows.Forms.View.Details;
			// 
			// chDb
			// 
			this.chDb.Text = "Database";
			this.chDb.Width = 102;
			// 
			// chUser
			// 
			this.chUser.Text = "User";
			this.chUser.Width = 112;
			// 
			// chStatus
			// 
			this.chStatus.Text = "Status";
			this.chStatus.Width = 132;
			// 
			// tmrAutoConn
			// 
			this.tmrAutoConn.Enabled = true;
			this.tmrAutoConn.Interval = 2000;
			this.tmrAutoConn.Tick += new System.EventHandler(this.timer1_Tick);
			// 
			// frmAutoConn
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(352, 309);
			this.Controls.Add(this.lvAutoConn);
			this.Controls.Add(this.button1);
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "frmAutoConn";
			this.Text = "Auto Connecting";
			this.ResumeLayout(false);

		}
		#endregion

		private void timer1_Tick(object sender, System.EventArgs e)
		{
			counter++;
			if (counter == 1)
			{
				this.lvAutoConn.Items[0].SubItems[2].Text = "Connected";
				this.lvAutoConn.Items[1].SubItems[2].Text = "Connecting...";
			}
			else if (counter == 2)
			{
				this.lvAutoConn.Items[1].SubItems[2].Text = "Connected";
				this.lvAutoConn.Items[2].SubItems[2].Text = "Connecting...";
			}
			else if (counter == 3)
			{
				this.lvAutoConn.Items[2].SubItems[2].Text = "Connected";
				this.tmrAutoConn.Interval = 500;
			}
			else if (counter == 4)
			{
				this.tmrAutoConn.Stop();
				this.Close();
			}
		}

		private void button1_Click(object sender, System.EventArgs e)
		{
			this.tmrAutoConn.Stop();
			this.Close();
		}
	}
}
