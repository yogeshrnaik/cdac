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
	/// Summary description for ObjectNamePrompt.
	/// </summary>
/***************************************************************************/
	public class ObjectNamePrompt : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button btnOK;
		private System.Windows.Forms.Button btnCancel;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
/***************************************************************************/
		public DBManagerMainForm parent;
		public bool showCancelBtn;
		private System.Windows.Forms.Label lblObjName;
		public System.Windows.Forms.TextBox txtObjectName;
		public string object_type;
		public bool cancelClicked;
/***************************************************************************/
		public ObjectNamePrompt(DBManagerMainForm parent, bool showCancelBtn, string object_type)
		{
			InitializeComponent();
			this.parent = parent;
			this.showCancelBtn = showCancelBtn;
			if (!showCancelBtn)
			{
				this.btnCancel.Hide();
				this.btnOK.Location = new Point((this.Width - this.btnOK.Width)/2, this.btnOK.Location.Y);
			}
			this.object_type = object_type;
			this.Text = "Enter " + this.object_type + " Name";
			this.lblObjName.Text = this.object_type + " Name:";
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
			this.txtObjectName = new System.Windows.Forms.TextBox();
			this.lblObjName = new System.Windows.Forms.Label();
			this.btnOK = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// txtObjectName
			// 
			this.txtObjectName.Location = new System.Drawing.Point(176, 24);
			this.txtObjectName.Name = "txtObjectName";
			this.txtObjectName.Size = new System.Drawing.Size(120, 20);
			this.txtObjectName.TabIndex = 0;
			this.txtObjectName.Text = "";
			// 
			// lblObjName
			// 
			this.lblObjName.Location = new System.Drawing.Point(40, 24);
			this.lblObjName.Name = "lblObjName";
			this.lblObjName.Size = new System.Drawing.Size(112, 16);
			this.lblObjName.TabIndex = 1;
			this.lblObjName.Text = "Object Name:";
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
			// ObjectNamePrompt
			// 
			this.AcceptButton = this.btnOK;
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.CancelButton = this.btnCancel;
			this.ClientSize = new System.Drawing.Size(314, 130);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnOK);
			this.Controls.Add(this.lblObjName);
			this.Controls.Add(this.txtObjectName);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Name = "ObjectNamePrompt";
			this.ShowInTaskbar = false;
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Enter Object Name";
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void btnOK_Click(object sender, System.EventArgs e)
		{
			if (txtObjectName.Text.Trim().Length == 0)
			{
				MessageBox.Show(this.object_type + " name cannot be blank.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				return;
			}
			string objName = txtObjectName.Text.Trim();
			//check whether object already there
			switch(this.object_type.ToUpper())
			{
				case "FUNCTION" : if (this.parent.getActiveConnection().database.getFunction(this.parent.getActiveSchema(), objName) != null)
								  {
										MessageBox.Show(object_type + " '" + txtObjectName.Text.Trim() + "' already exists.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
										return;
								  }
									break;
				case "PROCEDURE" : if (this.parent.getActiveConnection().database.getProcedure(this.parent.getActiveSchema(), objName) != null)
								  {
										MessageBox.Show(object_type + " '" + txtObjectName.Text.Trim() + "' already exists.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
										return;
								  }
									break;
				case "TRIGGER" : if (this.parent.getActiveConnection().database.getTrigger(this.parent.getActiveSchema(), objName) != null)
								  {
										MessageBox.Show(object_type + " '" + txtObjectName.Text.Trim() + "' already exists.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
										return;
								  }
									break;
				case "VIEW" : if (this.parent.getActiveConnection().database.getView(this.parent.getActiveSchema(), objName) != null)
								  {
										MessageBox.Show(object_type + " '" + txtObjectName.Text.Trim() + "' already exists.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
										return;
								  }
									break;
			};
			cancelClicked = false;
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