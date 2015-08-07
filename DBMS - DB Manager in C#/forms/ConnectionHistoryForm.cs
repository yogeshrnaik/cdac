/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;

using DBManager.forms;
using DBManager.classes;

using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
/***************************************************************************/
namespace DBManager.forms
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
/***************************************************************************/
	public class ConnectionHistoryForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Label lblConnHist;
		private System.Windows.Forms.DataGrid dtgridConnHist;
		private System.Windows.Forms.Panel panconnhist;
		private System.Windows.Forms.DataGridTableStyle ts;
		private System.Windows.Forms.DataGridTextBoxColumn column;
		private System.Windows.Forms.DataGridTextBoxColumn column2;
		private System.Windows.Forms.DataGridTextBoxColumn column3;
		private System.Windows.Forms.DataGridBoolColumn bcol;
		private System.Windows.Forms.Button btnNewConn;
		private System.Windows.Forms.Button btnLogin;
		private System.Windows.Forms.Button btnClose;
		private System.Windows.Forms.DataGridBoolColumn bcol1;
		private System.Windows.Forms.DataGridBoolColumn bcol2;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.PictureBox pictureBox1;
		private System.Windows.Forms.LinkLabel llbltns;
		private System.Windows.Forms.LinkLabel llblsqlnet;
		private System.Windows.Forms.PictureBox pictureBox2;
		private System.ComponentModel.IContainer components;
		public System.Windows.Forms.CheckBox chkAutoConnectAll;
		public System.Windows.Forms.CheckBox chkSaveAllPwds;
		public ConnectionHistory conHist;
		public SqlnetForm frmSqlnet;
		public TnsoraForm frmTnsora;
		public DBManagerMainForm parent;
/***************************************************************************/
		public ConnectionHistoryForm(SqlnetForm frmSqlnet, TnsoraForm frmTnsora, DBManagerMainForm parent)
		{
			// Required for Windows Form Designer support
			InitializeComponent();
			
			this.frmSqlnet = frmSqlnet;
			this.frmTnsora = frmTnsora;
			this.parent = parent;
			
			//MessageBox.Show("ConnectionHistoryForm");
			//init the data grid, check boxes
			init_connection_history_form();
		}
/***************************************************************************/
		public void init_connection_history_form()
		{
			this.MinimizeBox = false;
			this.MaximizeBox = false;
			this.ShowInTaskbar = false;

			//init ConnectionHistory object from binary file
			init_connectionHistory();
			
			//init the checkboxes
			if (this.conHist.connHistTable.Rows.Count == 0)
			{
				this.chkAutoConnectAll.Enabled = false;
				this.chkSaveAllPwds.Enabled = false;
			}
			else
			{
				this.chkAutoConnectAll.Enabled = true;
				this.chkSaveAllPwds.Enabled = true;
				this.chkAutoConnectAll.Checked = this.conHist.autoConnectAll;
				this.chkSaveAllPwds.Checked = this.conHist.saveAllPwds;
			}
			//Mittal
			this.dtgridConnHist.MouseDown += new MouseEventHandler(dtgridConnHist_MouseDown);
		}
/***************************************************************************/
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
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
/***************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(ConnectionHistoryForm));
			this.lblConnHist = new System.Windows.Forms.Label();
			this.panconnhist = new System.Windows.Forms.Panel();
			this.btnLogin = new System.Windows.Forms.Button();
			this.btnClose = new System.Windows.Forms.Button();
			this.btnNewConn = new System.Windows.Forms.Button();
			this.dtgridConnHist = new System.Windows.Forms.DataGrid();
			this.ts = new System.Windows.Forms.DataGridTableStyle();
			this.column = new System.Windows.Forms.DataGridTextBoxColumn();
			this.column2 = new System.Windows.Forms.DataGridTextBoxColumn();
			this.column3 = new System.Windows.Forms.DataGridTextBoxColumn();
			this.bcol = new System.Windows.Forms.DataGridBoolColumn();
			this.bcol1 = new System.Windows.Forms.DataGridBoolColumn();
			this.bcol2 = new System.Windows.Forms.DataGridBoolColumn();
			this.chkAutoConnectAll = new System.Windows.Forms.CheckBox();
			this.chkSaveAllPwds = new System.Windows.Forms.CheckBox();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.pictureBox2 = new System.Windows.Forms.PictureBox();
			this.pictureBox1 = new System.Windows.Forms.PictureBox();
			this.llbltns = new System.Windows.Forms.LinkLabel();
			this.llblsqlnet = new System.Windows.Forms.LinkLabel();
			this.panconnhist.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.dtgridConnHist)).BeginInit();
			this.groupBox1.SuspendLayout();
			this.SuspendLayout();
			// 
			// lblConnHist
			// 
			this.lblConnHist.Dock = System.Windows.Forms.DockStyle.Top;
			this.lblConnHist.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.lblConnHist.Location = new System.Drawing.Point(0, 0);
			this.lblConnHist.Name = "lblConnHist";
			this.lblConnHist.Size = new System.Drawing.Size(728, 24);
			this.lblConnHist.TabIndex = 0;
			this.lblConnHist.Text = "Connection History";
			this.lblConnHist.TextAlign = System.Drawing.ContentAlignment.TopCenter;
			// 
			// panconnhist
			// 
			this.panconnhist.Controls.Add(this.btnLogin);
			this.panconnhist.Controls.Add(this.btnClose);
			this.panconnhist.Controls.Add(this.btnNewConn);
			this.panconnhist.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.panconnhist.Location = new System.Drawing.Point(0, 445);
			this.panconnhist.Name = "panconnhist";
			this.panconnhist.Size = new System.Drawing.Size(728, 40);
			this.panconnhist.TabIndex = 1;
			// 
			// btnLogin
			// 
			this.btnLogin.Location = new System.Drawing.Point(320, 8);
			this.btnLogin.Name = "btnLogin";
			this.btnLogin.Size = new System.Drawing.Size(112, 23);
			this.btnLogin.TabIndex = 1;
			this.btnLogin.Text = "Login";
			this.btnLogin.Click += new System.EventHandler(this.btnLogin_Click);
			// 
			// btnClose
			// 
			this.btnClose.DialogResult = System.Windows.Forms.DialogResult.Cancel;
			this.btnClose.Location = new System.Drawing.Point(440, 8);
			this.btnClose.Name = "btnClose";
			this.btnClose.Size = new System.Drawing.Size(112, 23);
			this.btnClose.TabIndex = 2;
			this.btnClose.Text = "Close";
			this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
			// 
			// btnNewConn
			// 
			this.btnNewConn.Location = new System.Drawing.Point(8, 8);
			this.btnNewConn.Name = "btnNewConn";
			this.btnNewConn.Size = new System.Drawing.Size(112, 23);
			this.btnNewConn.TabIndex = 0;
			this.btnNewConn.Text = "New Connection";
			this.btnNewConn.Click += new System.EventHandler(this.btnNewConn_Click);
			// 
			// dtgridConnHist
			// 
			this.dtgridConnHist.DataMember = "";
			this.dtgridConnHist.Dock = System.Windows.Forms.DockStyle.Left;
			this.dtgridConnHist.HeaderForeColor = System.Drawing.Color.Black;
			this.dtgridConnHist.Location = new System.Drawing.Point(0, 24);
			this.dtgridConnHist.Name = "dtgridConnHist";
			this.dtgridConnHist.ReadOnly = true;
			this.dtgridConnHist.Size = new System.Drawing.Size(552, 421);
			this.dtgridConnHist.TabIndex = 3;
			this.dtgridConnHist.TableStyles.AddRange(new System.Windows.Forms.DataGridTableStyle[] {
																									   this.ts,
																									   this.ts});
			// 
			// ts
			// 
			this.ts.DataGrid = this.dtgridConnHist;
			this.ts.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.ts.MappingName = "";
			// 
			// column
			// 
			this.column.Format = "";
			this.column.FormatInfo = null;
			this.column.MappingName = "";
			this.column.Width = -1;
			// 
			// column2
			// 
			this.column2.Format = "";
			this.column2.FormatInfo = null;
			this.column2.MappingName = "";
			this.column2.Width = -1;
			// 
			// column3
			// 
			this.column3.Format = "";
			this.column3.FormatInfo = null;
			this.column3.MappingName = "";
			this.column3.Width = -1;
			// 
			// bcol
			// 
			this.bcol.FalseValue = false;
			this.bcol.MappingName = "";
			this.bcol.NullValue = ((object)(resources.GetObject("bcol.NullValue")));
			this.bcol.TrueValue = true;
			this.bcol.Width = -1;
			// 
			// bcol1
			// 
			this.bcol1.FalseValue = false;
			this.bcol1.MappingName = "";
			this.bcol1.NullValue = ((object)(resources.GetObject("bcol1.NullValue")));
			this.bcol1.TrueValue = true;
			this.bcol1.Width = -1;
			// 
			// bcol2
			// 
			this.bcol2.FalseValue = false;
			this.bcol2.MappingName = "";
			this.bcol2.NullValue = ((object)(resources.GetObject("bcol2.NullValue")));
			this.bcol2.TrueValue = true;
			this.bcol2.Width = -1;
			// 
			// chkAutoConnectAll
			// 
			this.chkAutoConnectAll.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkAutoConnectAll.Location = new System.Drawing.Point(560, 376);
			this.chkAutoConnectAll.Name = "chkAutoConnectAll";
			this.chkAutoConnectAll.Size = new System.Drawing.Size(120, 24);
			this.chkAutoConnectAll.TabIndex = 5;
			this.chkAutoConnectAll.Text = "Auto Connect All";
			this.chkAutoConnectAll.CheckedChanged += new System.EventHandler(this.chkAutoConnectAll_CheckedChanged);
			// 
			// chkSaveAllPwds
			// 
			this.chkSaveAllPwds.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.chkSaveAllPwds.Location = new System.Drawing.Point(560, 416);
			this.chkSaveAllPwds.Name = "chkSaveAllPwds";
			this.chkSaveAllPwds.TabIndex = 4;
			this.chkSaveAllPwds.Text = "Save All Pwds";
			this.chkSaveAllPwds.CheckedChanged += new System.EventHandler(this.chkSaveAllPwds_CheckedChanged);
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.pictureBox2);
			this.groupBox1.Controls.Add(this.pictureBox1);
			this.groupBox1.Controls.Add(this.llbltns);
			this.groupBox1.Controls.Add(this.llblsqlnet);
			this.groupBox1.Location = new System.Drawing.Point(568, 88);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(136, 112);
			this.groupBox1.TabIndex = 10;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "Oracle";
			// 
			// pictureBox2
			// 
			this.pictureBox2.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox2.Image")));
			this.pictureBox2.Location = new System.Drawing.Point(102, 25);
			this.pictureBox2.Name = "pictureBox2";
			this.pictureBox2.Size = new System.Drawing.Size(24, 24);
			this.pictureBox2.TabIndex = 14;
			this.pictureBox2.TabStop = false;
			// 
			// pictureBox1
			// 
			this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
			this.pictureBox1.Location = new System.Drawing.Point(102, 70);
			this.pictureBox1.Name = "pictureBox1";
			this.pictureBox1.Size = new System.Drawing.Size(24, 24);
			this.pictureBox1.TabIndex = 12;
			this.pictureBox1.TabStop = false;
			// 
			// llbltns
			// 
			this.llbltns.Location = new System.Drawing.Point(14, 78);
			this.llbltns.Name = "llbltns";
			this.llbltns.Size = new System.Drawing.Size(80, 16);
			this.llbltns.TabIndex = 11;
			this.llbltns.TabStop = true;
			this.llbltns.Text = "tnsnames.ora";
			this.llbltns.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.llbltns_LinkClicked);
			// 
			// llblsqlnet
			// 
			this.llblsqlnet.Location = new System.Drawing.Point(14, 25);
			this.llblsqlnet.Name = "llblsqlnet";
			this.llblsqlnet.Size = new System.Drawing.Size(80, 16);
			this.llblsqlnet.TabIndex = 10;
			this.llblsqlnet.TabStop = true;
			this.llblsqlnet.Text = "sqlnet.ora";
			this.llblsqlnet.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.llblsqlnet_LinkClicked);
			// 
			// ConnectionHistoryForm
			// 
			this.AcceptButton = this.btnLogin;
			this.AutoScaleBaseSize = new System.Drawing.Size(6, 13);
			this.CancelButton = this.btnClose;
			this.ClientSize = new System.Drawing.Size(728, 485);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.chkAutoConnectAll);
			this.Controls.Add(this.chkSaveAllPwds);
			this.Controls.Add(this.dtgridConnHist);
			this.Controls.Add(this.panconnhist);
			this.Controls.Add(this.lblConnHist);
			this.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "ConnectionHistoryForm";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Tag = "Connection History";
			this.Text = "Connection History";
			this.panconnhist.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.dtgridConnHist)).EndInit();
			this.groupBox1.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
//		[STAThread]
//		static void Main() 
//		{
//			Application.Run(new Form1());
//		}
/***************************************************************************/
		private void chkSaveAllPwds_CheckedChanged(object sender, System.EventArgs e)
		{
			DataTable dt = (DataTable)this.dtgridConnHist.DataSource;
			if (dt.Rows.Count == 0 && this.chkSaveAllPwds.Checked)
			{
				//this.chkSaveAllPwds.CheckedChanged -= new EventHandler(this.chkSaveAllPwds_CheckedChanged);
				//MessageBox.Show("No connection history available.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				this.chkSaveAllPwds.Checked = false;
				//this.chkSaveAllPwds.CheckedChanged += new EventHandler(this.chkSaveAllPwds_CheckedChanged);
				return;
			}
			for( int i = 0 ; i < dt.Rows.Count; i++)
			{
				dt.Columns["Save_passwd"].ReadOnly = false;
				dt.Rows[i]["Save_passwd"] = this.chkSaveAllPwds.Checked;
				dt.Columns["Save_passwd"].ReadOnly = true;
			}
		}
/***************************************************************************/
		private void chkAutoConnectAll_CheckedChanged(object sender, System.EventArgs e)
		{
			DataTable dt = (DataTable)this.dtgridConnHist.DataSource;
			if (dt.Rows.Count == 0 && this.chkAutoConnectAll.Checked)
			{
				this.chkAutoConnectAll.Checked = false;
				return;
			}
			for( int i = 0 ; i < dt.Rows.Count; i++)
			{
				dt.Columns["Auto_conn"].ReadOnly = false;
				dt.Rows[i]["Auto_conn"] = this.chkAutoConnectAll.Checked;
				dt.Columns["Auto_conn"].ReadOnly = true;
			}
		}
/***************************************************************************/
		private void btnNewConn_Click(object sender, System.EventArgs e)
		{
			Form frmConn = new frmNewConn(this);
			frmConn.ShowDialog();
		}
/***************************************************************************/
		private void btnClose_Click(object sender, System.EventArgs e)
		{
			saveConnectionHistoryToFile();
			this.Close();
		}
/***************************************************************************/
		private void llblsqlnet_LinkClicked(object sender, System.Windows.Forms.LinkLabelLinkClickedEventArgs e)
		{
			frmSqlnet.ShowDialog();
		}
/***************************************************************************/
		private void llbltns_LinkClicked(object sender, System.Windows.Forms.LinkLabelLinkClickedEventArgs e)
		{
			frmTnsora.ShowDialog();
		}
/***************************************************************************/
		private void btnLogin_Click(object sender, System.EventArgs e)
		{
			if (this.conHist.connHistTable.Rows.Count == 0)
			{
				if (MessageBox.Show("Connection history is not available. Do you want to make a New Connection?", 
					"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
				{
					frmNewConn frm = new frmNewConn(this);
					frm.ShowDialog();
				}
				return;
			}
			else if (this.dtgridConnHist.CurrentRowIndex >= 0)
			{
				DataTable dt = (DataTable)dtgridConnHist.DataSource;
				DataRow dr = dt.Rows[this.dtgridConnHist.CurrentRowIndex];
				string passwd = dr["Password"].ToString();
				//MessageBox.Show(passwd);
				if (dr["Save_Passwd"].ToString().Equals(true.ToString()) && passwd.Length > 0)
				{
					string msg = this.parent.connectToDatabase(dr["User"].ToString(), dr["Database"].ToString(), 
																dr["Password"].ToString());
					if (msg.Equals("SUCCESS"))
					{
						this.Hide();
					}
					else if (msg.Equals("ALREADY CONNECTED"))
					{
						string message = "User : '" + dr["User"].ToString() + "' is already connected " +
											"to database : '" + dr["Database"].ToString() + "'.";
						MessageBox.Show(message, "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					}
					else
					{
						if (msg.ToUpper().IndexOf("Password entered is invalid.".ToUpper()) >= 0)
						{
							MessageBox.Show("Saved Password is invalid. Enter the correct password.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
							//prompt for password
							PasswordPrompt pp = new PasswordPrompt(this.parent, dr["User"].ToString(), dr["Database"].ToString());
							pp.ShowDialog();
							if (!pp.cancelClicked && dr["Save_Passwd"].ToString().Equals(true.ToString()))
							{
								dr["Password"] = pp.txtPassword;
								dr["Save_Passwd"] = true;
							}
						}
					}
				}
				else
				{
					//prompt for password
					PasswordPrompt pp = new PasswordPrompt(this.parent, dr["User"].ToString(), dr["Database"].ToString());
					pp.ShowDialog();
					if (!pp.cancelClicked && dr["Save_Passwd"].ToString().Equals(true.ToString()))
					{
						dr["Password"] = pp.txtPassword;
					}
					//MessageBox.Show("Show a dialog box and prompt for password.");
				}
			}
			saveConnectionHistoryToFile();
		}
/***************************************************************************/
		private void init_connectionHistory()
		{
			//read from file and create ConnectionHistory object here or create a new object 
			try 
			{
				IFormatter formatter = new BinaryFormatter();
				//MessageBox.Show("1");
				Stream stream = new FileStream("conhist.bin", FileMode.Open, FileAccess.Read, FileShare.Read);
				//MessageBox.Show("2");
				conHist = (ConnectionHistory) formatter.Deserialize(stream);
				//MessageBox.Show("3");
				stream.Close();
				//MessageBox.Show("4");
			}
			catch
			{
				//MessageBox.Show("exception");
				conHist = new ConnectionHistory();
			}
			this.dtgridConnHist.DataSource = conHist.connHistTable;
			dtgridConnHist.TableStyles.Add(conHist.getTableStyle());
		}
/***************************************************************************/
		public void saveConnectionHistoryToFile()
		{
			conHist.autoConnectAll = this.chkAutoConnectAll.Checked;
			conHist.saveAllPwds = this.chkSaveAllPwds.Checked;
			conHist.connHistTable = (DataTable)this.dtgridConnHist.DataSource;

			try 
			{
				IFormatter formatter = new BinaryFormatter();
				Stream stream = new FileStream("conhist.bin", FileMode.Create, FileAccess.Write, FileShare.None);
				formatter.Serialize(stream, conHist);
				stream.Close();
			}
			catch
			{
				MessageBox.Show("", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}
/***************************************************************************/
		private void dtgridConnHist_MouseDown(object sender, System.Windows.Forms.MouseEventArgs me)
		{
			DataTable dt = (DataTable)this.dtgridConnHist.DataSource;
			dt.Columns["Save_passwd"].ReadOnly = false;
			dt.Columns["Auto_conn"].ReadOnly = false;
			int l_row = this.dtgridConnHist.HitTest(me.X,me.Y).Row;
			int l_col = this.dtgridConnHist.HitTest(me.X,me.Y).Column;
			if (l_row > -1 && l_row <= dt.Rows.Count && l_col > 2 && l_col <= dt.Columns.Count)
			{
				if (dt.Rows[l_row][l_col].Equals(false))
				{
					dt.Rows[l_row][l_col] = true;
				}
				else
				{
					dt.Rows[l_row][l_col] = false;
				}
				if (l_col == 3 && dt.Rows[l_row][l_col].Equals(true)) //save password column
				{
					//since save password is clicked save the password.
					PasswordPrompt pp = new PasswordPrompt(true);
					pp.ShowDialog();
					dt.Rows[l_row]["Password"] = pp.txtPassword.Text;
				}
			}
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/