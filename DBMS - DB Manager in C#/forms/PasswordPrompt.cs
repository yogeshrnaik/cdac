/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
/***************************************************************************/
namespace DBManager.forms
{
/***************************************************************************/
	/// <summary>
	/// Summary description for SchemaNamePrompt.
	/// </summary>
	/***************************************************************************/
	public class PasswordPrompt : System.Windows.Forms.Form
	{
/***************************************************************************/
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Button btnOK;
		private System.Windows.Forms.Button btnCancel;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		public System.Windows.Forms.TextBox txtPassword;
/***************************************************************************/
		public DBManagerMainForm parent;
		public string user;
		public string database;
		public bool justGetPassword;
		public bool cancelClicked;
/***************************************************************************/
		public PasswordPrompt(DBManagerMainForm parent, string user, string db)
		{
			InitializeComponent();
			this.parent = parent;
			this.user = user;
			this.database = db;
			this.justGetPassword = false;
		}
/***************************************************************************/
		public PasswordPrompt(bool justGetPassword)
		{
			InitializeComponent();
			this.justGetPassword = true;
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
			this.txtPassword = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.btnOK = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// txtPassword
			// 
			this.txtPassword.Location = new System.Drawing.Point(136, 23);
			this.txtPassword.Name = "txtPassword";
			this.txtPassword.PasswordChar = '*';
			this.txtPassword.Size = new System.Drawing.Size(152, 20);
			this.txtPassword.TabIndex = 0;
			this.txtPassword.Text = "";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(40, 23);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(88, 16);
			this.label1.TabIndex = 1;
			this.label1.Text = "Password:";
			// 
			// btnOK
			// 
			this.btnOK.Location = new System.Drawing.Point(64, 80);
			this.btnOK.Name = "btnOK";
			this.btnOK.TabIndex = 2;
			this.btnOK.Text = "OK";
			this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
			this.btnCancel.Location = new System.Drawing.Point(176, 80);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.TabIndex = 3;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// PasswordPrompt
			// 
			this.AcceptButton = this.btnOK;
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.CancelButton = this.btnCancel;
			this.ClientSize = new System.Drawing.Size(314, 130);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnOK);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.txtPassword);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Name = "PasswordPrompt";
			this.ShowInTaskbar = false;
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Enter Password";
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void btnOK_Click(object sender, System.EventArgs e)
		{
			cancelClicked = false;
			if (txtPassword.Text.Trim().Length == 0)
			{
				MessageBox.Show("Password cannot be blank.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				return;
			}
			if (justGetPassword)
			{
				this.Hide();
			}
			else
			{
				string msg = this.parent.connectToDatabase(user, database, this.txtPassword.Text);
				if (msg.Equals("SUCCESS"))
				{
					this.Hide();
				}
				else if (msg.Equals("ALREADY CONNECTED"))
				{
					string message = "User : '" + user + "' is already connected " +
						"to database : '" + database + "'.";
					MessageBox.Show(message, "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				}
				else
				{
					MessageBox.Show(msg, "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					if (msg.ToUpper().IndexOf("Password entered is invalid.".ToUpper()) >= 0)
					{
						return;	//do not hide
					}
						
				}
			}
			this.Hide();
		}
/***************************************************************************/
		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			cancelClicked = true;
			this.Hide();
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/