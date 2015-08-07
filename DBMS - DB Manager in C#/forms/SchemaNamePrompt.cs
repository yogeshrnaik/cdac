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
	public class SchemaNamePrompt : System.Windows.Forms.Form
	{
/***************************************************************************/
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Button btnOK;
		private System.Windows.Forms.Button btnCancel;
		private System.Windows.Forms.TextBox txtSchemaName;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		public DBManagerMainForm parent;
		public bool showCancelBtn;
/***************************************************************************/
		public SchemaNamePrompt(DBManagerMainForm parent, bool showCancelBtn)
		{
			InitializeComponent();
			this.parent = parent;
			this.showCancelBtn = showCancelBtn;
			if (!showCancelBtn)
			{
				this.btnCancel.Hide();
				this.btnOK.Location = new Point((this.Width - this.btnOK.Width)/2, this.btnOK.Location.Y);
			}
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
			this.txtSchemaName = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.btnOK = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// txtSchemaName
			// 
			this.txtSchemaName.Location = new System.Drawing.Point(136, 24);
			this.txtSchemaName.Name = "txtSchemaName";
			this.txtSchemaName.Size = new System.Drawing.Size(152, 20);
			this.txtSchemaName.TabIndex = 0;
			this.txtSchemaName.Text = "";
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(40, 24);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(88, 16);
			this.label1.TabIndex = 1;
			this.label1.Text = "Schema Name:";
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
			// SchemaNamePrompt
			// 
			this.AcceptButton = this.btnOK;
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.CancelButton = this.btnCancel;
			this.ClientSize = new System.Drawing.Size(314, 130);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnOK);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.txtSchemaName);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Name = "SchemaNamePrompt";
			this.ShowInTaskbar = false;
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Enter Schema Name";
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void btnOK_Click(object sender, System.EventArgs e)
		{
			if (txtSchemaName.Text.Trim().Length == 0)
			{
				MessageBox.Show("Schema name cannot be blank.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				return;
			}
			if (this.parent.getActiveConnection().database.getSchema(this.txtSchemaName.Text) == null)
			{
				if (parent.addSchema(this.txtSchemaName.Text))
				{
					MessageBox.Show("Schema added sucessfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					//after adding schema save the database
					this.parent.getActiveConnection().saveDatabase();
					this.Hide();
				}
			}
			else
			{
				MessageBox.Show("Schema already exists.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
			}
			
		}
/***************************************************************************/
		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			this.Hide();
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/