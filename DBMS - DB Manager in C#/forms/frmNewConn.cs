/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
/***************************************************************************/
namespace DBManager.forms
{
	/// <summary>
	/// Summary description for frmNewConn.
	/// </summary>
/***************************************************************************/
	public class frmNewConn : System.Windows.Forms.Form
	{
/***************************************************************************/
		private System.Windows.Forms.Label lblDb;
		private System.Windows.Forms.Label lblUser;
		private System.Windows.Forms.Label lblPasswd;
		private System.Windows.Forms.TextBox txtDb;
		private System.Windows.Forms.TextBox txtUser;
		private System.Windows.Forms.TextBox txtPasswd;
		private System.Windows.Forms.Button btnConnect;
		private System.Windows.Forms.Button btnCancel;
		private System.Windows.Forms.CheckBox chkbSavePasswd;
		private System.Windows.Forms.CheckBox chkbAutoConn;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		public ConnectionHistoryForm parent;
/***************************************************************************/
		public frmNewConn(ConnectionHistoryForm parent)
		{
			// Required for Windows Form Designer support
			InitializeComponent();
			
			this.parent = parent;
			this.MinimizeBox = false;
			this.MaximizeBox = false;
			this.ShowInTaskbar = false;
		}
/***************************************************************************/
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
/***************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.lblDb = new System.Windows.Forms.Label();
			this.lblUser = new System.Windows.Forms.Label();
			this.lblPasswd = new System.Windows.Forms.Label();
			this.txtDb = new System.Windows.Forms.TextBox();
			this.txtUser = new System.Windows.Forms.TextBox();
			this.txtPasswd = new System.Windows.Forms.TextBox();
			this.btnConnect = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.chkbSavePasswd = new System.Windows.Forms.CheckBox();
			this.chkbAutoConn = new System.Windows.Forms.CheckBox();
			this.SuspendLayout();
			// 
			// lblDb
			// 
			this.lblDb.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.lblDb.Location = new System.Drawing.Point(40, 24);
			this.lblDb.Name = "lblDb";
			this.lblDb.Size = new System.Drawing.Size(64, 16);
			this.lblDb.TabIndex = 0;
			this.lblDb.Text = "Database :";
			// 
			// lblUser
			// 
			this.lblUser.Location = new System.Drawing.Point(40, 56);
			this.lblUser.Name = "lblUser";
			this.lblUser.Size = new System.Drawing.Size(40, 16);
			this.lblUser.TabIndex = 1;
			this.lblUser.Text = "User :";
			// 
			// lblPasswd
			// 
			this.lblPasswd.Location = new System.Drawing.Point(40, 88);
			this.lblPasswd.Name = "lblPasswd";
			this.lblPasswd.Size = new System.Drawing.Size(64, 16);
			this.lblPasswd.TabIndex = 2;
			this.lblPasswd.Text = "Password :";
			// 
			// txtDb
			// 
			this.txtDb.Location = new System.Drawing.Point(104, 24);
			this.txtDb.Name = "txtDb";
			this.txtDb.TabIndex = 3;
			this.txtDb.Text = "";
			// 
			// txtUser
			// 
			this.txtUser.Location = new System.Drawing.Point(104, 56);
			this.txtUser.Name = "txtUser";
			this.txtUser.TabIndex = 4;
			this.txtUser.Text = "";
			// 
			// txtPasswd
			// 
			this.txtPasswd.Location = new System.Drawing.Point(104, 88);
			this.txtPasswd.Name = "txtPasswd";
			this.txtPasswd.PasswordChar = '*';
			this.txtPasswd.TabIndex = 5;
			this.txtPasswd.Text = "";
			// 
			// btnConnect
			// 
			this.btnConnect.Location = new System.Drawing.Point(24, 160);
			this.btnConnect.Name = "btnConnect";
			this.btnConnect.Size = new System.Drawing.Size(64, 23);
			this.btnConnect.TabIndex = 6;
			this.btnConnect.Text = "Connect";
			this.btnConnect.Click += new System.EventHandler(this.btnConnect_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
			this.btnCancel.Location = new System.Drawing.Point(136, 160);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.Size = new System.Drawing.Size(72, 24);
			this.btnCancel.TabIndex = 7;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// chkbSavePasswd
			// 
			this.chkbSavePasswd.Location = new System.Drawing.Point(24, 128);
			this.chkbSavePasswd.Name = "chkbSavePasswd";
			this.chkbSavePasswd.Size = new System.Drawing.Size(96, 16);
			this.chkbSavePasswd.TabIndex = 8;
			this.chkbSavePasswd.Text = "Save Passwd";
			// 
			// chkbAutoConn
			// 
			this.chkbAutoConn.Location = new System.Drawing.Point(128, 128);
			this.chkbAutoConn.Name = "chkbAutoConn";
			this.chkbAutoConn.Size = new System.Drawing.Size(80, 16);
			this.chkbAutoConn.TabIndex = 9;
			this.chkbAutoConn.Text = "Auto Connect";
			// 
			// frmNewConn
			// 
			this.AcceptButton = this.btnConnect;
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.CancelButton = this.btnCancel;
			this.ClientSize = new System.Drawing.Size(240, 197);
			this.Controls.Add(this.chkbAutoConn);
			this.Controls.Add(this.chkbSavePasswd);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnConnect);
			this.Controls.Add(this.txtPasswd);
			this.Controls.Add(this.txtUser);
			this.Controls.Add(this.txtDb);
			this.Controls.Add(this.lblPasswd);
			this.Controls.Add(this.lblUser);
			this.Controls.Add(this.lblDb);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "frmNewConn";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
			this.Text = "New Connection";
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			this.Hide();
		}
/***************************************************************************/
		private void btnConnect_Click(object sender, System.EventArgs e)
		{
			//validate that user has entered database, username and password
			//connect to the database
			string message = this.parent.parent.connectToDatabase(this.txtUser.Text, this.txtDb.Text, this.txtPasswd.Text);
			if (message.Equals("SUCCESS"))
			{
				//if connection is successful then add the connection details in the connection history
				this.parent.conHist.addConHistoryRow(this.txtUser.Text, this.txtDb.Text, DateTime.Now,
														this.chkbSavePasswd.Checked, this.chkbAutoConn.Checked,
														this.txtPasswd.Text);
				this.parent.chkAutoConnectAll.Enabled = true;
				this.parent.chkSaveAllPwds.Enabled = true;

				//and serialize after adding new entry
				this.parent.saveConnectionHistoryToFile();

				//hide all forms
				this.Hide();
				//this.parent.Hide();
			}
			else
			{
				string error = message;
				MessageBox.Show(error, "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/