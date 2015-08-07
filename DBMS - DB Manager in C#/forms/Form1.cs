using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

namespace WindowsApplication1
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Panel panel1;
		private System.Windows.Forms.Button btnRefresh;
		private System.Windows.Forms.CheckBox chkViewSql;
		private System.Windows.Forms.RichTextBox txtViewSql;
		private System.Windows.Forms.Button btnClose;
		private System.Windows.Forms.Button btnHelp;
		private System.Windows.Forms.Button btnApply;
		private System.Windows.Forms.Label label10;
		private System.Windows.Forms.Label label9;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.TextBox txtMaxVal;
		private System.Windows.Forms.TextBox txtMinVal;
		private System.Windows.Forms.TextBox txtName;
		private System.Windows.Forms.TextBox txtOwner;
		private System.Windows.Forms.TextBox txtCacheSize;
		private System.Windows.Forms.TextBox txtIncrBy;
		private System.Windows.Forms.TextBox txtNextNum;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.CheckBox chkOrder;
		private System.Windows.Forms.CheckBox chkCycle;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label1;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			txtViewSql.Visible=false;
			
			btnRefresh.Enabled = false;
			btnApply.Enabled = false;


			btnRefresh.Click+=new EventHandler(btnRefresh_Click);
			btnClose.Click+=new EventHandler(btnClose_Click);
			btnApply.Click+=new EventHandler(btnApply_Click);
	
			txtNextNum.TextChanged += new System.EventHandler(this.txtNextNum_TextChanged);
			txtIncrBy.TextChanged += new System.EventHandler(this.txtIncrBy_TextChanged);
			chkCycle.CheckedChanged += new System.EventHandler(this.chkCycle_CheckedChanged);
			chkOrder.CheckedChanged += new System.EventHandler(this.chkOrder_CheckedChanged);
			txtMaxVal.TextChanged += new System.EventHandler(this.txtMaxVal_TextChanged);
			txtMinVal.TextChanged += new System.EventHandler(this.txtMinVal_TextChanged);
			txtCacheSize.TextChanged+=new EventHandler(txtCacheSize_TextChanged);

			
			

			

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// 
		void btnRefresh_Click(object obj, EventArgs ea)
		{
			this.txtNextNum.Text = "22835";
			this.txtIncrBy.Text = "1";
			this.txtCacheSize.Text = "20";
			this.txtMinVal.Text = "1";
			this.txtMaxVal.Text = "9999999999999999";
			this.chkCycle.Checked=false;
			this.chkOrder.Checked=false;
			this.btnApply.Enabled=false;
			this.btnRefresh.Enabled=false;
			chkViewSqlOnCheckChanged(obj,ea);
			
		}
		void chkViewSqlOnCheckChanged(object obj, EventArgs ea)
		{
			if(chkViewSql.Checked)
			{	
				
				txtViewSql.Visible=true;
				txtViewSql.Text="";
				txtViewSql.SelectionColor=Color.Gray;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Italic);
				txtViewSql.SelectedText="--create sequence";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\n\ncreate sequence  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtName.Text;

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nminvalue  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtMinVal.Text;

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nmaxvalue  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtMaxVal.Text;

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nstarts with  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtNextNum.Text;

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nincrement by  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtIncrBy.Text;

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\ncache  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtCacheSize.Text;






				
				label1.Visible=false;
				label2.Visible=false;
				label3.Visible=false;
				label4.Visible=false;
				label5.Visible=false;
				txtNextNum.Visible=false;
				txtIncrBy.Visible=false;
				txtCacheSize.Visible=false;
				chkCycle.Visible=false;
				chkOrder.Visible=false;
				txtOwner.Visible=false;
				txtName.Visible=false;
				txtMinVal.Visible=false;
				txtMaxVal.Visible=false;
				label6.Visible=false;
				label7.Visible=false;
				label9.Visible=false;
				label10.Visible=false;
			}
			else
			{
				txtViewSql.Visible=false;
				label1.Visible=true;
				label2.Visible=true;
				label3.Visible=true;
				label4.Visible=true;
				label5.Visible=true;
				txtNextNum.Visible=true;
				txtIncrBy.Visible=true;
				txtCacheSize.Visible=true;
				chkCycle.Visible=true;
				chkOrder.Visible=true;
				txtOwner.Visible=true;
				txtName.Visible=true;
				txtMinVal.Visible=true;
				txtMaxVal.Visible=true;
				label6.Visible=true;
				label7.Visible=true;
				label9.Visible=true;
				label10.Visible=true;
			}
		}
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
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
			this.panel1 = new System.Windows.Forms.Panel();
			this.btnRefresh = new System.Windows.Forms.Button();
			this.chkViewSql = new System.Windows.Forms.CheckBox();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnHelp = new System.Windows.Forms.Button();
			this.btnApply = new System.Windows.Forms.Button();
			this.label10 = new System.Windows.Forms.Label();
			this.label9 = new System.Windows.Forms.Label();
			this.label7 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.txtMaxVal = new System.Windows.Forms.TextBox();
			this.txtMinVal = new System.Windows.Forms.TextBox();
			this.txtName = new System.Windows.Forms.TextBox();
			this.txtOwner = new System.Windows.Forms.TextBox();
			this.txtCacheSize = new System.Windows.Forms.TextBox();
			this.txtIncrBy = new System.Windows.Forms.TextBox();
			this.txtNextNum = new System.Windows.Forms.TextBox();
			this.label5 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.chkOrder = new System.Windows.Forms.CheckBox();
			this.chkCycle = new System.Windows.Forms.CheckBox();
			this.label3 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.txtViewSql = new System.Windows.Forms.RichTextBox();
			this.panel1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.panel1.Controls.Add(this.btnRefresh);
			this.panel1.Controls.Add(this.chkViewSql);
			this.panel1.Controls.Add(this.btnClose);
			this.panel1.Controls.Add(this.btnHelp);
			this.panel1.Controls.Add(this.btnApply);
			this.panel1.Controls.Add(this.label10);
			this.panel1.Controls.Add(this.label9);
			this.panel1.Controls.Add(this.label7);
			this.panel1.Controls.Add(this.label6);
			this.panel1.Controls.Add(this.txtMaxVal);
			this.panel1.Controls.Add(this.txtMinVal);
			this.panel1.Controls.Add(this.txtName);
			this.panel1.Controls.Add(this.txtOwner);
			this.panel1.Controls.Add(this.txtCacheSize);
			this.panel1.Controls.Add(this.txtIncrBy);
			this.panel1.Controls.Add(this.txtNextNum);
			this.panel1.Controls.Add(this.label5);
			this.panel1.Controls.Add(this.label4);
			this.panel1.Controls.Add(this.chkOrder);
			this.panel1.Controls.Add(this.chkCycle);
			this.panel1.Controls.Add(this.label3);
			this.panel1.Controls.Add(this.label2);
			this.panel1.Controls.Add(this.label1);
			this.panel1.Controls.Add(this.txtViewSql);
			this.panel1.Location = new System.Drawing.Point(16, 0);
			this.panel1.Name = "panel1";
			this.panel1.Size = new System.Drawing.Size(560, 216);
			this.panel1.TabIndex = 25;
			// 
			// btnRefresh
			// 
			this.btnRefresh.Location = new System.Drawing.Point(112, 176);
			this.btnRefresh.Name = "btnRefresh";
			this.btnRefresh.Size = new System.Drawing.Size(64, 24);
			this.btnRefresh.TabIndex = 48;
			this.btnRefresh.Text = "Refresh";
			this.btnRefresh.Click += new System.EventHandler(this.btnRefresh_Click_1);
			// 
			// chkViewSql
			// 
			this.chkViewSql.Appearance = System.Windows.Forms.Appearance.Button;
			this.chkViewSql.Location = new System.Drawing.Point(432, 176);
			this.chkViewSql.Name = "chkViewSql";
			this.chkViewSql.Size = new System.Drawing.Size(64, 24);
			this.chkViewSql.TabIndex = 47;
			this.chkViewSql.Text = "View Sql";
			this.chkViewSql.Click += new System.EventHandler(this.chkViewSqlOnCheckChanged);
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(184, 176);
			this.btnClose.Name = "btnClose";
			this.btnClose.Size = new System.Drawing.Size(64, 24);
			this.btnClose.TabIndex = 35;
			this.btnClose.Text = "Close";
			// 
			// btnHelp
			// 
			this.btnHelp.Location = new System.Drawing.Point(256, 176);
			this.btnHelp.Name = "btnHelp";
			this.btnHelp.Size = new System.Drawing.Size(64, 24);
			this.btnHelp.TabIndex = 36;
			this.btnHelp.Text = "Help";
			// 
			// btnApply
			// 
			this.btnApply.Location = new System.Drawing.Point(40, 176);
			this.btnApply.Name = "btnApply";
			this.btnApply.Size = new System.Drawing.Size(64, 24);
			this.btnApply.TabIndex = 34;
			this.btnApply.Text = "Apply";
			// 
			// label10
			// 
			this.label10.Location = new System.Drawing.Point(64, 90);
			this.label10.Name = "label10";
			this.label10.Size = new System.Drawing.Size(57, 16);
			this.label10.TabIndex = 39;
			this.label10.Text = "Min Value";
			// 
			// label9
			// 
			this.label9.Location = new System.Drawing.Point(64, 120);
			this.label9.Name = "label9";
			this.label9.Size = new System.Drawing.Size(61, 16);
			this.label9.TabIndex = 40;
			this.label9.Text = "Max Value";
			// 
			// label7
			// 
			this.label7.Location = new System.Drawing.Point(88, 56);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(40, 16);
			this.label7.TabIndex = 38;
			this.label7.Text = "Name";
			// 
			// label6
			// 
			this.label6.Location = new System.Drawing.Point(80, 27);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(40, 16);
			this.label6.TabIndex = 37;
			this.label6.Text = "Owner";
			// 
			// txtMaxVal
			// 
			this.txtMaxVal.Location = new System.Drawing.Point(128, 119);
			this.txtMaxVal.Name = "txtMaxVal";
			this.txtMaxVal.Size = new System.Drawing.Size(136, 20);
			this.txtMaxVal.TabIndex = 26;
			this.txtMaxVal.Text = "9999999999999999";
			// 
			// txtMinVal
			// 
			this.txtMinVal.Location = new System.Drawing.Point(128, 88);
			this.txtMinVal.Name = "txtMinVal";
			this.txtMinVal.Size = new System.Drawing.Size(136, 20);
			this.txtMinVal.TabIndex = 25;
			this.txtMinVal.Text = "1";
			// 
			// txtName
			// 
			this.txtName.Enabled = false;
			this.txtName.Location = new System.Drawing.Point(128, 56);
			this.txtName.Name = "txtName";
			this.txtName.Size = new System.Drawing.Size(136, 20);
			this.txtName.TabIndex = 29;
			this.txtName.Text = "CTAB_ACS_ID_SEQ";
			// 
			// txtOwner
			// 
			this.txtOwner.Enabled = false;
			this.txtOwner.Location = new System.Drawing.Point(128, 24);
			this.txtOwner.Name = "txtOwner";
			this.txtOwner.Size = new System.Drawing.Size(136, 20);
			this.txtOwner.TabIndex = 27;
			this.txtOwner.Text = "INTAPPS";
			// 
			// txtCacheSize
			// 
			this.txtCacheSize.Location = new System.Drawing.Point(392, 89);
			this.txtCacheSize.Name = "txtCacheSize";
			this.txtCacheSize.Size = new System.Drawing.Size(136, 20);
			this.txtCacheSize.TabIndex = 31;
			this.txtCacheSize.Text = "20";
			// 
			// txtIncrBy
			// 
			this.txtIncrBy.Location = new System.Drawing.Point(392, 56);
			this.txtIncrBy.Name = "txtIncrBy";
			this.txtIncrBy.Size = new System.Drawing.Size(136, 20);
			this.txtIncrBy.TabIndex = 30;
			this.txtIncrBy.Text = "1";
			// 
			// txtNextNum
			// 
			this.txtNextNum.Location = new System.Drawing.Point(392, 24);
			this.txtNextNum.Name = "txtNextNum";
			this.txtNextNum.Size = new System.Drawing.Size(136, 20);
			this.txtNextNum.TabIndex = 28;
			this.txtNextNum.Text = "22835";
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(480, 130);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(40, 16);
			this.label5.TabIndex = 45;
			this.label5.Text = "Order";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(408, 129);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(40, 16);
			this.label4.TabIndex = 44;
			this.label4.Text = "Cycle";
			// 
			// chkOrder
			// 
			this.chkOrder.Location = new System.Drawing.Point(456, 128);
			this.chkOrder.Name = "chkOrder";
			this.chkOrder.Size = new System.Drawing.Size(16, 16);
			this.chkOrder.TabIndex = 33;
			this.chkOrder.Text = "checkBox2";
			// 
			// chkCycle
			// 
			this.chkCycle.Location = new System.Drawing.Point(392, 128);
			this.chkCycle.Name = "chkCycle";
			this.chkCycle.Size = new System.Drawing.Size(16, 16);
			this.chkCycle.TabIndex = 32;
			this.chkCycle.Text = "checkBox1";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(328, 90);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(104, 16);
			this.label3.TabIndex = 43;
			this.label3.Text = "Cache size";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(320, 59);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(72, 16);
			this.label2.TabIndex = 42;
			this.label2.Text = "Increment by";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(320, 32);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(72, 16);
			this.label1.TabIndex = 41;
			this.label1.Text = "Next number";
			// 
			// txtViewSql
			// 
			this.txtViewSql.Location = new System.Drawing.Point(32, 8);
			this.txtViewSql.Name = "txtViewSql";
			this.txtViewSql.ReadOnly = true;
			this.txtViewSql.Size = new System.Drawing.Size(504, 160);
			this.txtViewSql.TabIndex = 46;
			this.txtViewSql.Text = "";
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(592, 229);
			this.Controls.Add(this.panel1);
			this.Name = "Form1";
			this.Text = "Edit sequence";
			this.Load += new System.EventHandler(this.Form1_Load);
			this.panel1.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
//		static void Main() 
//		{
//			Application.Run(new Form1());
//		}

		private void Form1_Load(object sender, System.EventArgs e)
		{
		
		}

		private void dataGrid1_Navigate(object sender, System.Windows.Forms.NavigateEventArgs ne)
		{
		
		}

		private void label1_Click(object sender, System.EventArgs e)
		{
		
		}

		private void label2_Click(object sender, System.EventArgs e)
		{
		
		}

		private void txtNextNum_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		

		private void txtIncrBy_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void txtMinVal_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void txtMaxVal_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void txtCacheSize_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void chkCycle_CheckedChanged(object  sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void chkOrder_CheckedChanged(object  sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}

		private void btnClose_Click(object  sender, System.EventArgs e)
		{
			this.Close();
		}

		private void btnApply_Click(object sender, System.EventArgs e)
		{
			MessageBox.Show("Sure you want to update the sequence ?","update sequences",MessageBoxButtons.OKCancel,MessageBoxIcon.Question);
		}

		private void btnRefresh_Click_1(object sender, System.EventArgs e)
		{
		
		}

		
		
		
		
	}
}
