/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using DBManager.forms;
/***************************************************************************/
namespace DBManager.classes
{
/***************************************************************************/
	/// <summary>
	/// Summary description for DBLinkPanel.
	/// </summary>
/***************************************************************************/
	public class DBLinkPanel : Panel
	{
/***************************************************************************/
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.CheckBox chkShared;
		private System.Windows.Forms.CheckBox chkPublic;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox txtName;
		private System.Windows.Forms.TextBox txtOwner;
		private System.Windows.Forms.GroupBox groupBox2;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.TextBox txtUser;
		private System.Windows.Forms.TextBox txtPwd;
		private System.Windows.Forms.CheckBox chkCurrent;
		private System.Windows.Forms.GroupBox groupBox3;
		private System.Windows.Forms.TextBox txtAuthUser;
		private System.Windows.Forms.TextBox txtAuthPwd;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Button btnApply;
		private System.Windows.Forms.Button btnRefresh;
		private System.Windows.Forms.Button btnClose;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.CheckBox checkBox2;
		private System.Windows.Forms.TextBox txtDB;
		private System.Windows.Forms.RichTextBox txtDBSql;
		private System.Windows.Forms.GroupBox groupBox4;
		private System.Windows.Forms.Label label8;

		public string dbLinkName;
		public string schema_name;
		public string created_by;
		public Database database;
		public TabPage myTabPage;
		private DBLink dbTemp;
/***************************************************************************/
		public DBLinkPanel(Database database, string schema, string DBLinkName, string username)
		{
			//
			// TODO: Add constructor logic here
			//
			InitializeComponent();
			this.database = database;
			this.schema_name = schema;
			this.dbLinkName = DBLinkName;
			this.created_by = username;
			this.Dock = DockStyle.Fill;

			this.txtName.TextChanged += new System.EventHandler(this.txtName_TextChanged);
			this.checkBox2.CheckedChanged += new System.EventHandler(this.checkBox2_CheckedChanged);
			this.txtAuthPwd.TextChanged += new System.EventHandler(this.txtAuthPwd_TextChanged);
			this.txtAuthUser.TextChanged += new System.EventHandler(this.txtAuthUser_TextChanged);
			this.chkCurrent.CheckedChanged += new System.EventHandler(this.chkCurrent_CheckedChanged);
			this.txtDB.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
			this.txtPwd.TextChanged += new System.EventHandler(this.txtPwd_TextChanged);
			this.txtUser.TextChanged += new System.EventHandler(this.txtUser_TextChanged);
			this.chkShared.CheckedChanged += new System.EventHandler(this.chkShared_CheckedChanged);
			this.chkPublic.CheckedChanged += new System.EventHandler(this.chkPublic_CheckedChanged);
			
			populate_data();
		}
/***************************************************************************/		
		private void InitializeComponent()
		{
			this.txtDBSql = new System.Windows.Forms.RichTextBox();
			this.checkBox2 = new System.Windows.Forms.CheckBox();
			this.button1 = new System.Windows.Forms.Button();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnRefresh = new System.Windows.Forms.Button();
			this.btnApply = new System.Windows.Forms.Button();
			this.groupBox3 = new System.Windows.Forms.GroupBox();
			this.label5 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.txtAuthPwd = new System.Windows.Forms.TextBox();
			this.txtAuthUser = new System.Windows.Forms.TextBox();
			this.groupBox2 = new System.Windows.Forms.GroupBox();
			this.chkCurrent = new System.Windows.Forms.CheckBox();
			this.txtDB = new System.Windows.Forms.TextBox();
			this.txtPwd = new System.Windows.Forms.TextBox();
			this.txtUser = new System.Windows.Forms.TextBox();
			this.label7 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.chkShared = new System.Windows.Forms.CheckBox();
			this.chkPublic = new System.Windows.Forms.CheckBox();
			this.label3 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.txtName = new System.Windows.Forms.TextBox();
			this.txtOwner = new System.Windows.Forms.TextBox();
			this.groupBox4 = new System.Windows.Forms.GroupBox();
			this.label8 = new System.Windows.Forms.Label();
			
			this.groupBox3.SuspendLayout();
			this.groupBox2.SuspendLayout();
			this.groupBox1.SuspendLayout();
			this.SuspendLayout();
			// 
			// panel1
			// 
			this.Controls.Add(this.label8);
			this.Controls.Add(this.checkBox2);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.btnClose);
			this.Controls.Add(this.btnRefresh);
			this.Controls.Add(this.btnApply);
			this.Controls.Add(this.groupBox3);
			this.Controls.Add(this.groupBox2);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.txtDBSql);
			this.Controls.Add(this.groupBox4);
			this.Location = new System.Drawing.Point(40, 16);
			
			this.Size = new System.Drawing.Size(648, 384);
			this.TabIndex = 0;
			
			// 
			// txtDBSql
			// 
			this.txtDBSql.Location = new System.Drawing.Point(38, 88);
			this.txtDBSql.Name = "txtDBSql";
			this.txtDBSql.ReadOnly = true;
			this.txtDBSql.Size = new System.Drawing.Size(576, 216);
			this.txtDBSql.TabIndex = 0;
			this.txtDBSql.Text = "";
			this.txtDBSql.Visible = false;
			// 
			// checkBox2
			// 
			this.checkBox2.Appearance = System.Windows.Forms.Appearance.Button;
			this.checkBox2.Location = new System.Drawing.Point(534, 312);
			this.checkBox2.Name = "checkBox2";
			this.checkBox2.Size = new System.Drawing.Size(64, 24);
			this.checkBox2.TabIndex = 10;
			this.checkBox2.Text = "View SQL";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(318, 312);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(72, 24);
			this.button1.TabIndex = 9;
			this.button1.Text = "Help";
			// 
			// btnClose
			// 
			this.btnClose.Location = new System.Drawing.Point(230, 312);
			this.btnClose.Name = "btnClose";
			this.btnClose.Size = new System.Drawing.Size(72, 24);
			this.btnClose.TabIndex = 8;
			this.btnClose.Text = "Close";
			this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
			// 
			// btnRefresh
			// 
			this.btnRefresh.Enabled = false;
			this.btnRefresh.Location = new System.Drawing.Point(142, 312);
			this.btnRefresh.Name = "btnRefresh";
			this.btnRefresh.Size = new System.Drawing.Size(72, 24);
			this.btnRefresh.TabIndex = 7;
			this.btnRefresh.Text = "Refresh";
			this.btnRefresh.Click += new System.EventHandler(this.btnRefresh_Click);
			// 
			// btnApply
			// 
			this.btnApply.Enabled = false;
			this.btnApply.Location = new System.Drawing.Point(62, 312);
			this.btnApply.Name = "btnApply";
			this.btnApply.Size = new System.Drawing.Size(64, 24);
			this.btnApply.TabIndex = 6;
			this.btnApply.Text = "Apply";
			this.btnApply.Click += new System.EventHandler(this.btnApply_Click);
			// 
			// groupBox3
			// 
			this.groupBox3.Controls.Add(this.label5);
			this.groupBox3.Controls.Add(this.label4);
			this.groupBox3.Controls.Add(this.txtAuthPwd);
			this.groupBox3.Controls.Add(this.txtAuthUser);
			this.groupBox3.Location = new System.Drawing.Point(278, 208);
			this.groupBox3.Name = "groupBox3";
			this.groupBox3.Size = new System.Drawing.Size(328, 88);
			this.groupBox3.TabIndex = 5;
			this.groupBox3.TabStop = false;
			this.groupBox3.Text = "Authenticated By";
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(8, 56);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(64, 16);
			this.label5.TabIndex = 3;
			this.label5.Text = "Password:";
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(8, 32);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(64, 16);
			this.label4.TabIndex = 2;
			this.label4.Text = "Username:";
			// 
			// txtAuthPwd
			// 
			this.txtAuthPwd.Location = new System.Drawing.Point(80, 56);
			this.txtAuthPwd.Name = "txtAuthPwd";
			this.txtAuthPwd.Size = new System.Drawing.Size(152, 20);
			this.txtAuthPwd.TabIndex = 4;
			this.txtAuthPwd.PasswordChar = '*';
			
			// 
			// txtAuthUser
			// 
			this.txtAuthUser.Location = new System.Drawing.Point(80, 24);
			this.txtAuthUser.Name = "txtAuthUser";
			this.txtAuthUser.Size = new System.Drawing.Size(152, 20);
			this.txtAuthUser.TabIndex = 3;
			
			// 
			// groupBox2
			// 
			this.groupBox2.Controls.Add(this.chkCurrent);
			this.groupBox2.Controls.Add(this.txtDB);
			this.groupBox2.Controls.Add(this.txtPwd);
			this.groupBox2.Controls.Add(this.txtUser);
			this.groupBox2.Controls.Add(this.label7);
			this.groupBox2.Controls.Add(this.label6);
			this.groupBox2.Controls.Add(this.label1);
			this.groupBox2.Location = new System.Drawing.Point(278, 96);
			this.groupBox2.Name = "groupBox2";
			this.groupBox2.Size = new System.Drawing.Size(328, 112);
			this.groupBox2.TabIndex = 17;
			this.groupBox2.TabStop = false;
			this.groupBox2.Text = "Connect To";
			// 
			// chkCurrent
			// 
			this.chkCurrent.Location = new System.Drawing.Point(240, 24);
			this.chkCurrent.Name = "chkCurrent";
			this.chkCurrent.Size = new System.Drawing.Size(72, 24);
			this.chkCurrent.TabIndex = 5;
			this.chkCurrent.Text = "Current";
			// 
			// txtDB
			// 
			this.txtDB.Location = new System.Drawing.Point(72, 80);
			this.txtDB.Name = "txtDB";
			this.txtDB.Size = new System.Drawing.Size(160, 20);
			this.txtDB.TabIndex = 2;
			
			// 
			// txtPwd
			// 
			this.txtPwd.Location = new System.Drawing.Point(72, 48);
			this.txtPwd.Name = "txtPwd";
			this.txtPwd.PasswordChar = '*';
			this.txtPwd.Size = new System.Drawing.Size(160, 20);
			this.txtPwd.TabIndex = 1;
			
			// 
			// txtUser
			// 
			this.txtUser.Location = new System.Drawing.Point(72, 24);
			this.txtUser.Name = "txtUser";
			this.txtUser.Size = new System.Drawing.Size(160, 20);
			this.txtUser.TabIndex = 0;
			
			// 
			// label7
			// 
			this.label7.Location = new System.Drawing.Point(8, 80);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(64, 16);
			this.label7.TabIndex = 6;
			this.label7.Text = "Database:";
			// 
			// label6
			// 
			this.label6.Location = new System.Drawing.Point(8, 56);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(64, 16);
			this.label6.TabIndex = 5;
			this.label6.Text = "Password:";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(8, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(64, 16);
			this.label1.TabIndex = 4;
			this.label1.Text = "Username:";
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.chkShared);
			this.groupBox1.Controls.Add(this.chkPublic);
			this.groupBox1.Controls.Add(this.label3);
			this.groupBox1.Controls.Add(this.label2);
			this.groupBox1.Controls.Add(this.txtName);
			this.groupBox1.Controls.Add(this.txtOwner);
			this.groupBox1.Location = new System.Drawing.Point(62, 96);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(200, 200);
			this.groupBox1.TabIndex = 16;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "Database Link";
			// 
			// chkShared
			// 
			
			this.chkShared.Location = new System.Drawing.Point(76, 128);
			this.chkShared.Name = "chkShared";
			this.chkShared.Size = new System.Drawing.Size(92, 16);
			this.chkShared.TabIndex = 5;
			this.chkShared.Text = "Shared";
			// 
			// chkPublic
			// 
			
			this.chkPublic.Location = new System.Drawing.Point(76, 104);
			this.chkPublic.Name = "chkPublic";
			this.chkPublic.Size = new System.Drawing.Size(92, 16);
			this.chkPublic.TabIndex = 4;
			this.chkPublic.Text = "Public";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(20, 64);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(48, 16);
			this.label3.TabIndex = 2;
			this.label3.Text = "Name:";
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(20, 32);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(48, 16);
			this.label2.TabIndex = 0;
			this.label2.Text = "Owner:";
			// 
			// txtName
			// 
		
			this.txtName.Location = new System.Drawing.Point(76, 64);
			this.txtName.Name = "txtName";
			this.txtName.Size = new System.Drawing.Size(104, 20);
			this.txtName.TabIndex = 3;
			
			// 
			// txtOwner
			// 
			this.txtOwner.Enabled = false;
			this.txtOwner.Location = new System.Drawing.Point(76, 32);
			this.txtOwner.Name = "txtOwner";
			this.txtOwner.Size = new System.Drawing.Size(104, 20);
			this.txtOwner.TabIndex = 1;
			
			// 
			// groupBox4
			// 
			this.groupBox4.Location = new System.Drawing.Point(16, 64);
			this.groupBox4.Name = "groupBox4";
			this.groupBox4.Size = new System.Drawing.Size(616, 288);
			this.groupBox4.TabIndex = 18;
			this.groupBox4.TabStop = false;
			// 
			// label8
			// 
			this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label8.Location = new System.Drawing.Point(160, 32);
			this.label8.Name = "label8";
			this.label8.Size = new System.Drawing.Size(336, 24);
			this.label8.TabIndex = 19;
			this.label8.Text = "DB LINK";
			this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
		}
/***************************************************************************/
		private void txtName_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;		
		}
/***************************************************************************/
		private void textBox1_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void txtUser_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void txtPwd_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void txtAuthUser_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void txtAuthPwd_TextChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void chkCurrent_CheckedChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void chkPublic_CheckedChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
		private void chkShared_CheckedChanged(object sender, System.EventArgs e)
		{
			this.btnApply.Enabled=true;
			this.btnRefresh.Enabled=true;
		}
/***************************************************************************/
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
/***************************************************************************/
		private void btnRefresh_Click(object sender, System.EventArgs e)
		{
			populate_data();
		}
/***************************************************************************/
		private void btnApply_Click(object sender, System.EventArgs e)
		{
			string msg = "";
			if (this.dbLinkName.Length == 0)
				msg = "Add the DB Link, are you sure?";
			else
				msg = "Update the DB Link, are you sure?";

			if(MessageBox.Show(msg, "DB Manager", MessageBoxButtons.OKCancel,
				MessageBoxIcon.Question) == DialogResult.OK)
			{
				if(dbLinkName.Length == 0)
				{
					if (database.getDBLink(schema_name, this.txtName.Text) != null)
					{
						MessageBox.Show("DB Link named '" + this.txtName.Text + "' already exists. Choose another name for the new DB Link.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						return;
					}
					database.getSchema(schema_name).addDBLink(this.txtName.Text, created_by,
													System.DateTime.Now,
													txtUser.Text,
													txtPwd.Text,
													txtDB.Text,
													txtAuthUser.Text,
													txtAuthPwd.Text);
					
					
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm)tc.Parent;
					
					this.dbLinkName = this.txtName.Text;

					TreeNode tn = main.getDBObjectNode("DB LINKS");
					if (tn != null)
					{
						TreeNode dblinkNode = new TreeNode(this.txtName.Text,
							MyImageList.getImgList().getImageIndex("DBLINK"),
							MyImageList.getImgList().getImageIndex("DBLINK"));
						
						dblinkNode.Tag = "DBLINK";
						tn.Nodes.Add(dblinkNode);
						tn.Expand();
						tn.Text = "DB Links (" + main.getActiveConnection().database.getSchema(schema_name).dblinks.Count + ")";
					}
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("DB Link added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						this.btnRefresh.Enabled = false;
						this.txtName.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not add DB Link.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
				else
				{
					database.getSchema(schema_name).updateDBLink(dbLinkName, txtUser.Text, txtPwd.Text, txtDB.Text,
																txtAuthUser.Text,
																txtAuthPwd.Text);
					TabControl tc = (TabControl)this.myTabPage.Parent;
					DBManagerMainForm main = (DBManagerMainForm) tc.Parent;
					if (main.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("DB Link updated successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
						this.btnApply.Enabled = false;
						this.btnRefresh.Enabled = false;
					}
					else
					{
						MessageBox.Show("Could not update DB Link.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
			}
			else
			{
				this.btnRefresh.Enabled = true;
			}
		}
/***************************************************************************/
		private void checkBox2_CheckedChanged(object sender, System.EventArgs e)
		{
			if(this.checkBox2.Checked)
			{
				this.txtDBSql.Visible=true;
				this.label1.Visible=false;
				this.label2.Visible=false;
				this.label3.Visible=false;
				this.label4.Visible=false;
				this.label5.Visible=false;
				this.label6.Visible=false;
				this.label7.Visible=false;

				this.txtOwner.Visible=false;
				this.txtName.Visible=false;
				this.txtUser.Visible=false;
				this.txtPwd.Visible=false;
				this.txtDB.Visible=false;
				this.txtAuthUser.Visible=false;
				this.txtAuthPwd.Visible=false;

				this.chkPublic.Visible=false;
				this.chkShared.Visible=false;
				this.chkCurrent.Visible=false;

				this.groupBox1.Visible=false;
				this.groupBox2.Visible=false;
				this.groupBox3.Visible=false;


				txtDBSql.Visible=true;
				txtDBSql.Text="";
				txtDBSql.SelectionColor=Color.Gray;
				txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Italic);
				txtDBSql.SelectedText="--create database link ";

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtDBSql.SelectedText="\n\ndatabase link  ";

				
				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10);
				txtDBSql.SelectedText=this.txtName.Text;

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtDBSql.SelectedText="\nconnect to  ";

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10);
				txtDBSql.SelectedText=this.txtUser.Text;

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtDBSql.SelectedText="\nidentified by  ";

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10);
				txtDBSql.SelectedText=this.txtPwd.Text;

				if (txtAuthUser.Text != "")
				{
					txtDBSql.SelectionColor=Color.Black;
					txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
					txtDBSql.SelectedText="\nauthenticated by  ";

					txtDBSql.SelectionColor=Color.Black;
					txtDBSql.SelectionFont=new Font("Times New Roman",10);
					txtDBSql.SelectedText=this.txtAuthUser.Text;

					txtDBSql.SelectionColor=Color.Black;
					txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
					txtDBSql.SelectedText="\nidentified by  ";

					txtDBSql.SelectionColor=Color.Black;
					txtDBSql.SelectionFont=new Font("Times New Roman",10);
					txtDBSql.SelectedText=this.txtAuthPwd.Text;

					
				}

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10,FontStyle.Bold);
				txtDBSql.SelectedText="\nusing  ";

				txtDBSql.SelectionColor=Color.Black;
				txtDBSql.SelectionFont=new Font("Times New Roman",10);
				txtDBSql.SelectedText=this.txtDB.Text;
				
				
			}
			else
			{
				this.groupBox1.Visible=true;
				this.groupBox2.Visible=true;
				this.groupBox3.Visible=true;
				this.txtDBSql.Visible=false;
				this.label1.Visible=true;
				this.label2.Visible=true;
				this.label3.Visible=true;
				this.label4.Visible=true;
				this.label5.Visible=true;
				this.label6.Visible=true;
				this.label7.Visible=true;

				this.txtOwner.Visible=true;
				this.txtName.Visible=true;
				this.txtUser.Visible=true;
				this.txtPwd.Visible=true;
				this.txtDB.Visible=true;
				this.txtAuthUser.Visible=true;
				this.txtAuthPwd.Visible=true;

				this.chkPublic.Visible=true;
				this.chkShared.Visible=true;
				this.chkCurrent.Visible=true;
			}
		}
/***************************************************************************/
		public void populate_data()
		{
			this.txtOwner.Text = this.created_by;
			if (dbLinkName.Length == 0)
			{
				this.txtUser.Text = "";
				this.txtPwd.Text = "";
				this.txtAuthUser.Text = "";
				this.txtAuthPwd.Text = "";
				this.txtDBSql.Text = "";
				this.txtDB.Text = "";
				this.chkPublic.Checked=false;
				this.chkShared.Checked=false;
				this.chkCurrent.Checked=false;
				this.txtName.Enabled = true;
			}
			else
			{
				this.txtName.Enabled = false;
				this.txtName.Text = dbLinkName;
				//dbTemp=new DBLink(dbLinkName,created_by,System.DateTime.Today,txtUser,txtPwd,txtDB,txtAuthUser,txtAuthPwd);
				dbTemp = database.getDBLink(schema_name,dbLinkName);
				this.txtUser.Text = dbTemp.uname;
				this.txtPwd.Text = dbTemp.passwd;
				this.txtAuthUser.Text = dbTemp.authby_uname;
				this.txtAuthPwd.Text = dbTemp.authby_passwd;
				this.txtDB.Text = dbTemp.database;
			}
			this.btnApply.Enabled = false;
			this.btnRefresh.Enabled = false;
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/
