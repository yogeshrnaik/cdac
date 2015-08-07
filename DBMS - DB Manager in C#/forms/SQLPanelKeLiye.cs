using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;

namespace DBManager.forms
{
	/// <summary>
	/// Summary description for SQLPanelKeLiye.
	/// </summary>
	public class SQLPanelKeLiye : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Panel SQLWindowPanel;
		private System.Windows.Forms.Panel panSQL;
		private System.Windows.Forms.Splitter splitter2;
		private System.Windows.Forms.DataGrid grdSqlOutput;
		private System.Windows.Forms.ToolBar sqlWindowToolbar;
		private System.Windows.Forms.ToolBarButton prevSqlButton;
		private System.Windows.Forms.ToolBarButton nextSqlButton;
		private System.Windows.Forms.ToolBarButton execSqlButton;
		private System.Windows.Forms.RichTextBox txtSqlWindow;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public SQLPanelKeLiye()
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
			this.SQLWindowPanel = new System.Windows.Forms.Panel();
			this.panSQL = new System.Windows.Forms.Panel();
			this.splitter2 = new System.Windows.Forms.Splitter();
			this.sqlWindowToolbar = new System.Windows.Forms.ToolBar();
			this.grdSqlOutput = new System.Windows.Forms.DataGrid();
			this.prevSqlButton = new System.Windows.Forms.ToolBarButton();
			this.nextSqlButton = new System.Windows.Forms.ToolBarButton();
			this.execSqlButton = new System.Windows.Forms.ToolBarButton();
			this.txtSqlWindow = new System.Windows.Forms.RichTextBox();
			this.SQLWindowPanel.SuspendLayout();
			this.panSQL.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.grdSqlOutput)).BeginInit();
			this.SuspendLayout();
			// 
			// SQLWindowPanel
			// 
			this.SQLWindowPanel.Controls.Add(this.grdSqlOutput);
			this.SQLWindowPanel.Controls.Add(this.splitter2);
			this.SQLWindowPanel.Controls.Add(this.panSQL);
			this.SQLWindowPanel.Dock = System.Windows.Forms.DockStyle.Fill;
			this.SQLWindowPanel.Location = new System.Drawing.Point(0, 0);
			this.SQLWindowPanel.Name = "SQLWindowPanel";
			this.SQLWindowPanel.Size = new System.Drawing.Size(568, 429);
			this.SQLWindowPanel.TabIndex = 0;
			// 
			// panSQL
			// 
			this.panSQL.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.panSQL.Controls.Add(this.txtSqlWindow);
			this.panSQL.Controls.Add(this.sqlWindowToolbar);
			this.panSQL.Dock = System.Windows.Forms.DockStyle.Top;
			this.panSQL.Location = new System.Drawing.Point(0, 0);
			this.panSQL.Name = "panSQL";
			this.panSQL.Size = new System.Drawing.Size(568, 152);
			this.panSQL.TabIndex = 0;
			// 
			// splitter2
			// 
			this.splitter2.Dock = System.Windows.Forms.DockStyle.Top;
			this.splitter2.Location = new System.Drawing.Point(0, 152);
			this.splitter2.Name = "splitter2";
			this.splitter2.Size = new System.Drawing.Size(568, 8);
			this.splitter2.TabIndex = 1;
			this.splitter2.TabStop = false;
			// 
			// sqlWindowToolbar
			// 
			this.sqlWindowToolbar.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																								this.prevSqlButton,
																								this.nextSqlButton,
																								this.execSqlButton});
			this.sqlWindowToolbar.Dock = System.Windows.Forms.DockStyle.Right;
			this.sqlWindowToolbar.DropDownArrows = true;
			this.sqlWindowToolbar.Location = new System.Drawing.Point(548, 0);
			this.sqlWindowToolbar.Name = "sqlWindowToolbar";
			this.sqlWindowToolbar.ShowToolTips = true;
			this.sqlWindowToolbar.Size = new System.Drawing.Size(20, 152);
			this.sqlWindowToolbar.TabIndex = 1;
			// 
			// grdSqlOutput
			// 
			this.grdSqlOutput.DataMember = "";
			this.grdSqlOutput.Dock = System.Windows.Forms.DockStyle.Fill;
			this.grdSqlOutput.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.grdSqlOutput.HeaderForeColor = System.Drawing.SystemColors.ControlText;
			this.grdSqlOutput.Location = new System.Drawing.Point(0, 160);
			this.grdSqlOutput.Name = "grdSqlOutput";
			this.grdSqlOutput.Size = new System.Drawing.Size(568, 269);
			this.grdSqlOutput.TabIndex = 2;
			// 
			// prevSqlButton
			// 
			this.prevSqlButton.Tag = "1";
			this.prevSqlButton.Text = "P";
			// 
			// nextSqlButton
			// 
			this.nextSqlButton.Tag = "2";
			this.nextSqlButton.Text = "N";
			// 
			// execSqlButton
			// 
			this.execSqlButton.Tag = "3";
			this.execSqlButton.Text = "E";
			// 
			// txtSqlWindow
			// 
			this.txtSqlWindow.Dock = System.Windows.Forms.DockStyle.Fill;
			this.txtSqlWindow.Location = new System.Drawing.Point(0, 0);
			this.txtSqlWindow.Name = "txtSqlWindow";
			this.txtSqlWindow.Size = new System.Drawing.Size(548, 152);
			this.txtSqlWindow.TabIndex = 2;
			this.txtSqlWindow.Text = "richTextBox1";
			this.txtSqlWindow.TextChanged += new System.EventHandler(this.txtSqlWindow_TextChanged);
			// 
			// SQLPanelKeLiye
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(568, 429);
			this.Controls.Add(this.SQLWindowPanel);
			this.Name = "SQLPanelKeLiye";
			this.Text = "SQLPanelKeLiye";
			this.SQLWindowPanel.ResumeLayout(false);
			this.panSQL.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.grdSqlOutput)).EndInit();
			this.ResumeLayout(false);

		}
		#endregion

		private void txtSqlWindow_TextChanged(object sender, System.EventArgs e)
		{
		
		}
	}
}
