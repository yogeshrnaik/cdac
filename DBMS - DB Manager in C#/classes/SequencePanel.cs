/********************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using DBManager.forms;
/********************************************************************************/
namespace DBManager.classes
{
/********************************************************************************/
	/// <summary>
	/// Summary description for SequencePanel.
	/// </summary>
/********************************************************************************/
	public class SequencePanel : Panel
	{
/********************************************************************************/
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
		private System.Windows.Forms.TextBox txtStartWith;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.CheckBox chkOrder;
		private System.Windows.Forms.CheckBox chkCycle;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.Label label8;

		public string seqName;
		public string schema_name;
		public string created_by;
		public Database database;
		public TabPage myTabPage;
		private Sequence seqTmp;
/********************************************************************************/
		public SequencePanel(Database database, string schema, string seqName, string username)
		{
			InitializeComponent();
			
			this.database = database;
			this.schema_name = schema;
			this.seqName = seqName;
			this.created_by = username;
			
			populate_data();
			txtViewSql.Visible=false;
			btnRefresh.Enabled = false;
			btnApply.Enabled = false;

			btnRefresh.Click+=new EventHandler(this.btnRefresh_Click);
			btnClose.Click+=new EventHandler(btnClose_Click);
			btnApply.Click+=new EventHandler(btnApply_Click);
			btnHelp.Click+=new EventHandler(btnHelp_Click);
	
			txtStartWith.TextChanged += new System.EventHandler(this.txtStartWith_TextChanged);
			txtName.TextChanged += new System.EventHandler(this.txtName_TextChanged);
			txtIncrBy.TextChanged += new System.EventHandler(this.txtIncrBy_TextChanged);
			chkCycle.CheckedChanged += new System.EventHandler(this.chkCycle_CheckedChanged);
			chkOrder.CheckedChanged += new System.EventHandler(this.chkOrder_CheckedChanged);
			txtMaxVal.TextChanged += new System.EventHandler(this.txtMaxVal_TextChanged);
			txtMinVal.TextChanged += new System.EventHandler(this.txtMinVal_TextChanged);
			txtCacheSize.TextChanged+=new EventHandler(txtCacheSize_TextChanged);

			this.Dock = DockStyle.Fill;
		}
/********************************************************************************/
		private void InitializeComponent()
		{
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
			this.txtStartWith = new System.Windows.Forms.TextBox();
			this.label5 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.chkOrder = new System.Windows.Forms.CheckBox();
			this.chkCycle = new System.Windows.Forms.CheckBox();
			this.label3 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.txtViewSql = new System.Windows.Forms.RichTextBox();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.label8 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.Controls.Add(this.btnRefresh);
			this.Controls.Add(this.chkViewSql);
			this.Controls.Add(this.btnClose);
			this.Controls.Add(this.btnHelp);
			this.Controls.Add(this.btnApply);
			this.Controls.Add(this.label10);
			this.Controls.Add(this.label9);
			this.Controls.Add(this.label7);
			this.Controls.Add(this.label6);
			this.Controls.Add(this.txtMaxVal);
			this.Controls.Add(this.txtMinVal);
			this.Controls.Add(this.txtName);
			this.Controls.Add(this.txtOwner);
			this.Controls.Add(this.txtCacheSize);
			this.Controls.Add(this.txtIncrBy);
			this.Controls.Add(this.txtStartWith);
			this.Controls.Add(this.label5);
			this.Controls.Add(this.label4);
			this.Controls.Add(this.chkOrder);
			this.Controls.Add(this.chkCycle);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.txtViewSql);
			this.Controls.Add(this.label8);
			this.Controls.Add(this.groupBox1);

			this.Location = new System.Drawing.Point(16, 0);
			this.Name = "panel1";
			this.Size = new System.Drawing.Size(560, 216);
			
			
			// 
			// btnRefresh
			// 
			this.btnRefresh.Location = new System.Drawing.Point(112, 248);
			this.btnRefresh.Name = "btnRefresh";
			this.btnRefresh.Size = new System.Drawing.Size(64, 24);
			this.btnRefresh.TabIndex = 10;
			this.btnRefresh.Text = "Refresh";
			// 
			// chkViewSql
			// 
			this.chkViewSql.Appearance = System.Windows.Forms.Appearance.Button;
			this.chkViewSql.Location = new System.Drawing.Point(480, 248);
			this.chkViewSql.Name = "chkViewSql";
			this.chkViewSql.Size = new System.Drawing.Size(64, 24);
			this.chkViewSql.TabIndex = 13;
			this.chkViewSql.Text = "View Sql";
			this.chkViewSql.Click += new System.EventHandler(this.chkViewSqlOnCheckChanged);
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(184, 248);
			this.btnClose.Name = "btnClose";
			this.btnClose.Size = new System.Drawing.Size(64, 24);
			this.btnClose.TabIndex = 11;
			this.btnClose.Text = "Close";
			// 
			// btnHelp
			// 
			this.btnHelp.Location = new System.Drawing.Point(256, 248);
			this.btnHelp.Name = "btnHelp";
			this.btnHelp.Size = new System.Drawing.Size(64, 24);
			this.btnHelp.TabIndex = 12;
			this.btnHelp.Text = "Help";
			// 
			// btnApply
			// 
			this.btnApply.Location = new System.Drawing.Point(40, 248);
			this.btnApply.Name = "btnApply";
			this.btnApply.Size = new System.Drawing.Size(64, 24);
			this.btnApply.TabIndex = 9;
			this.btnApply.Text = "Apply";
			// 
			// label10
			// 
			this.label10.Location = new System.Drawing.Point(56, 160);
			this.label10.Name = "label10";
			this.label10.Size = new System.Drawing.Size(59, 16);
			this.label10.Text = "Min Value:";
			// 
			// label9
			// 
			this.label9.Location = new System.Drawing.Point(56, 192);
			this.label9.Name = "label9";
			this.label9.Size = new System.Drawing.Size(61, 16);
			this.label9.Text = "Max Value:";
			// 
			// label7
			// 
			this.label7.Location = new System.Drawing.Point(56, 128);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(40, 16);
			this.label7.Text = "Name:";
			// 
			// label6
			// 
			this.label6.Location = new System.Drawing.Point(56, 96);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(42, 16);
			this.label6.Text = "Owner:";

			
			// 
			// txtMaxVal
			// 
			this.txtMaxVal.Location = new System.Drawing.Point(120, 192);
			this.txtMaxVal.Name = "txtMaxVal";
			this.txtMaxVal.Size = new System.Drawing.Size(136, 20);
			this.txtMaxVal.TabIndex = 3;
			
			
			// 
			// txtMinVal
			// 
			this.txtMinVal.Location = new System.Drawing.Point(120, 160);
			this.txtMinVal.Name = "txtMinVal";
			this.txtMinVal.Size = new System.Drawing.Size(136, 20);
			this.txtMinVal.TabIndex = 2;

			// 
			// txtName
			// 
			
			this.txtName.Location = new System.Drawing.Point(120, 128);
			this.txtName.Name = "txtName";
			this.txtName.Size = new System.Drawing.Size(136, 20);
			this.txtName.TabIndex = 1;
			
			// 
			// txtOwner
			// 
			this.txtOwner.Enabled = false;
			this.txtOwner.Location = new System.Drawing.Point(120, 96);
			this.txtOwner.Name = "txtOwner";
			this.txtOwner.Size = new System.Drawing.Size(136, 20);
			
			
			// 
			// txtCacheSize
			// 
			this.txtCacheSize.Location = new System.Drawing.Point(384, 160);
			this.txtCacheSize.Name = "txtCacheSize";
			this.txtCacheSize.Size = new System.Drawing.Size(136, 20);
			this.txtCacheSize.TabIndex = 6;
			// 
			// txtIncrBy
			// 
			this.txtIncrBy.Location = new System.Drawing.Point(384, 128);
			this.txtIncrBy.Name = "txtIncrBy";
			this.txtIncrBy.Size = new System.Drawing.Size(136, 20);
			this.txtIncrBy.TabIndex = 5;
			// 
			// txtStartWith
			// 
			this.txtStartWith.Location = new System.Drawing.Point(384, 96);
			this.txtStartWith.Name = "txtStartWith";
			this.txtStartWith.Size = new System.Drawing.Size(136, 20);
			this.txtStartWith.TabIndex = 4;
		
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(472, 200);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(40, 16);
			this.label5.Text = "Order";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(400, 200);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(40, 16);
			this.label4.Text = "Cycle";
			// 
			// chkOrder
			// 
			this.chkOrder.Location = new System.Drawing.Point(448, 200);
			this.chkOrder.Name = "chkOrder";
			this.chkOrder.Size = new System.Drawing.Size(16, 16);
			this.chkOrder.TabIndex = 8;
			this.chkOrder.Text = "checkBox2";
			// 
			// chkCycle
			// 
			this.chkCycle.Location = new System.Drawing.Point(384, 200);
			this.chkCycle.Name = "chkCycle";
			this.chkCycle.Size = new System.Drawing.Size(16, 16);
			this.chkCycle.TabIndex = 7;
			this.chkCycle.Text = "checkBox1";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(312, 160);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(104, 16);
			this.label3.Text = "Cache size:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(312, 128);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(74, 16);
			this.label2.Text = "Increment by:";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(312, 104);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(72, 16);
			this.label1.Text = "Starts with:";
			// 
			// txtViewSql
			// 
			this.txtViewSql.Location = new System.Drawing.Point(16, 24);
			this.txtViewSql.Name = "txtViewSql";
			this.txtViewSql.ReadOnly = true;
			this.txtViewSql.Size = new System.Drawing.Size(504, 160);
			this.txtViewSql.TabIndex = 14;
			this.txtViewSql.Text = "";
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.txtViewSql);
			this.groupBox1.Location = new System.Drawing.Point(24, 48);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(536, 232);
			this.groupBox1.TabStop = false;
			
			// 
			// label8
			// 
			this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label8.Location = new System.Drawing.Point(120, 16);
			this.label8.Name = "label8";
			this.label8.Size = new System.Drawing.Size(328, 35);
			this.label8.Text = "SEQUENCE";
			this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			
			

		}
/********************************************************************************/
		
		private void txtName_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void txtStartWith_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void txtIncrBy_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void txtMinVal_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void txtMaxVal_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void txtCacheSize_TextChanged(object sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void chkCycle_CheckedChanged(object  sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void chkOrder_CheckedChanged(object  sender, System.EventArgs e)
		{
			this.btnRefresh.Enabled = true;
			this.btnApply.Enabled = true;
		}
/********************************************************************************/
		private void btnClose_Click(object  sender, System.EventArgs e)
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
		private void btnHelp_Click(object sender, System.EventArgs e)
		{
			Help.ShowHelpIndex(this, "TOAD.chm");
		}	
/********************************************************************************/
		private void btnApply_Click(object sender, System.EventArgs e)
		{
			string msg = "";
			if (this.seqName.Length == 0)
				msg = "Add the sequence, are you sure?";
			else
				msg = "Update the sequence, are you sure?";

			if(MessageBox.Show(msg, "DB Manager", MessageBoxButtons.OKCancel,
				MessageBoxIcon.Question) == DialogResult.OK)
			{
				if(seqName.Length == 0)
				{
					if (database.getSequence(schema_name, this.txtName.Text) != null)
					{
						MessageBox.Show("Sequence named '" + this.txtName.Text + "' already exists. Choose another name for the new sequence.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						return;
					}
					database.getSchema(schema_name).addSequence(txtName.Text, created_by, System.DateTime.Today, 
						txtMinVal.Text,
						txtMaxVal.Text,
						txtStartWith.Text, 
						txtIncrBy.Text,
						txtCacheSize.Text,
						chkCycle.Checked, 
						chkOrder.Checked);
					
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm)tc.Parent;
					
					this.seqName = this.txtName.Text;

					TreeNode tn = main.getDBObjectNode("SEQUENCES");
					if (tn != null)
					{
						TreeNode seqNode = new TreeNode(this.txtName.Text,
							MyImageList.getImgList().getImageIndex("SEQUENCE"),
							MyImageList.getImgList().getImageIndex("SEQUENCE"));
						
						seqNode.Tag = "SEQUENCE";

						tn.Nodes.Add(seqNode);
						tn.Expand();
						tn.Text = "Sequences (" + main.getActiveConnection().database.getSchema(schema_name).sequences.Count + ")";
					}
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Sequence added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						this.btnRefresh.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not add sequence.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
				else
				{
					database.updateSequence(schema_name,txtName.Text,txtMinVal.Text,
						txtMaxVal.Text,
						txtStartWith.Text, 
						txtIncrBy.Text,
						txtCacheSize.Text,
						chkCycle.Checked,
						chkOrder.Checked);
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm) tc.Parent;
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Sequence updated successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						this.btnRefresh.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not update sequence.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
			}
			else
			{
				this.btnRefresh.Enabled = true;
			}
		}
/********************************************************************************/
		void btnRefresh_Click(object obj, EventArgs ea)
		{
			populate_data();
			this.btnApply.Enabled = false;
			this.btnRefresh.Enabled = false;
			chkViewSqlOnCheckChanged(obj,ea);
		}
/********************************************************************************/
		void chkViewSqlOnCheckChanged(object obj, EventArgs ea)
		{
			if(chkViewSql.Checked)
			{	
				txtViewSql.Visible=true;
				txtViewSql.Text="";
				txtViewSql.SelectionColor=Color.Red;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Italic);
				txtViewSql.SelectedText="--create sequence";

				txtViewSql.SelectionColor=Color.Blue;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\n\ncreate sequence  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtName.Text;

				txtViewSql.SelectionColor=Color.Blue;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nminvalue  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtMinVal.Text;

				txtViewSql.SelectionColor=Color.Blue;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nmaxvalue  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtMaxVal.Text;

				txtViewSql.SelectionColor=Color.Blue;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nstarts with  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtStartWith.Text;

				txtViewSql.SelectionColor=Color.Blue;
				txtViewSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtViewSql.SelectedText="\nincrement by  ";

				txtViewSql.SelectionColor=Color.Black;
				txtViewSql.SelectionFont=new Font("Times New Roman",10);
				txtViewSql.SelectedText=this.txtIncrBy.Text;

				txtViewSql.SelectionColor=Color.Blue;
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
				txtStartWith.Visible=false;
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
				txtStartWith.Visible=true;
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
/********************************************************************************/
		public void populate_data()
		{
			
			this.txtOwner.Text=this.created_by;
			if (seqName.Length == 0)
			{
				this.txtMaxVal.Text = "";
				this.txtStartWith.Text = "";
				this.txtCacheSize.Text = "";
				this.txtMinVal.Text = "";
				this.txtIncrBy.Text = "";
				this.chkCycle.Checked=false;
				this.chkOrder.Checked=false;
				this.txtName.Text ="";
				this.txtName.Enabled = true;
			}
			else 
			{	seqTmp = database.getSequence(schema_name , seqName);
				this.txtMaxVal.Text = seqTmp.max_value.ToString();
				this.txtMinVal.Text = seqTmp.min_value.ToString();
				this.txtStartWith.Text = seqTmp.start_with.ToString();
				this.txtIncrBy.Text = (String)seqTmp.increment_by.ToString();
				this.txtCacheSize.Text = (String)seqTmp.cache.ToString();
				this.chkOrder.Checked = seqTmp.order;
				this.chkCycle.Checked=seqTmp.cycle;
				this.txtName.Text = seqName;
				this.txtName.Enabled = false;
			}
		}
	}
/********************************************************************************/
}
/********************************************************************************/