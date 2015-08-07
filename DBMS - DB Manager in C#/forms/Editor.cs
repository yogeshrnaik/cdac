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
	/// Summary description for Editor.
	/// </summary>
/***************************************************************************/
	public class EditorForm : System.Windows.Forms.Form
	{
/***************************************************************************/
		private System.Windows.Forms.Panel EditorPanel;
		private System.Windows.Forms.Panel panEditor;
		private System.Windows.Forms.ToolBar toolBarEditor;
		private System.Windows.Forms.Splitter splitter1;
		private System.Windows.Forms.ListBox lbErrors;
		private System.Windows.Forms.ImageList imgListEditor;
		private System.Windows.Forms.ToolBarButton tbbSave;
		private System.Windows.Forms.ToolBarButton tbbCompile;
		private System.Windows.Forms.ToolBarButton tbbExecute;
		private System.Windows.Forms.RichTextBox rtbEditor;
		private System.ComponentModel.IContainer components;
/***************************************************************************/
		private bool runTextChange = false;
/***************************************************************************/
		public Editor()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
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
			this.components = new System.ComponentModel.Container();
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(Editor));
			this.EditorPanel = new System.Windows.Forms.Panel();
			this.panEditor = new System.Windows.Forms.Panel();
			this.toolBarEditor = new System.Windows.Forms.ToolBar();
			this.splitter1 = new System.Windows.Forms.Splitter();
			this.lbErrors = new System.Windows.Forms.ListBox();
			this.rtbEditor = new System.Windows.Forms.RichTextBox();
			this.imgListEditor = new System.Windows.Forms.ImageList(this.components);
			this.tbbSave = new System.Windows.Forms.ToolBarButton();
			this.tbbCompile = new System.Windows.Forms.ToolBarButton();
			this.tbbExecute = new System.Windows.Forms.ToolBarButton();
			this.EditorPanel.SuspendLayout();
			this.panEditor.SuspendLayout();
			this.SuspendLayout();
			// 
			// EditorPanel
			// 
			this.EditorPanel.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.EditorPanel.Controls.Add(this.lbErrors);
			this.EditorPanel.Controls.Add(this.splitter1);
			this.EditorPanel.Controls.Add(this.panEditor);
			this.EditorPanel.Dock = System.Windows.Forms.DockStyle.Fill;
			this.EditorPanel.Location = new System.Drawing.Point(0, 0);
			this.EditorPanel.Name = "EditorPanel";
			this.EditorPanel.Size = new System.Drawing.Size(608, 453);
			this.EditorPanel.TabIndex = 0;
			// 
			// panEditor
			// 
			this.panEditor.BackColor = System.Drawing.SystemColors.Control;
			this.panEditor.Controls.Add(this.rtbEditor);
			this.panEditor.Controls.Add(this.toolBarEditor);
			this.panEditor.Dock = System.Windows.Forms.DockStyle.Top;
			this.panEditor.Location = new System.Drawing.Point(0, 0);
			this.panEditor.Name = "panEditor";
			this.panEditor.Size = new System.Drawing.Size(608, 264);
			this.panEditor.TabIndex = 0;
			// 
			// toolBarEditor
			// 
			this.toolBarEditor.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																							 this.tbbSave,
																							 this.tbbCompile,
																							 this.tbbExecute});
			this.toolBarEditor.DropDownArrows = true;
			this.toolBarEditor.ImageList = this.imgListEditor;
			this.toolBarEditor.Location = new System.Drawing.Point(0, 0);
			this.toolBarEditor.Name = "toolBarEditor";
			this.toolBarEditor.ShowToolTips = true;
			this.toolBarEditor.Size = new System.Drawing.Size(608, 28);
			this.toolBarEditor.TabIndex = 0;
			// 
			// splitter1
			// 
			this.splitter1.BackColor = System.Drawing.SystemColors.Control;
			this.splitter1.Dock = System.Windows.Forms.DockStyle.Top;
			this.splitter1.Location = new System.Drawing.Point(0, 264);
			this.splitter1.Name = "splitter1";
			this.splitter1.Size = new System.Drawing.Size(608, 8);
			this.splitter1.TabIndex = 1;
			this.splitter1.TabStop = false;
			// 
			// lbErrors
			// 
			this.lbErrors.Dock = System.Windows.Forms.DockStyle.Fill;
			this.lbErrors.Location = new System.Drawing.Point(0, 272);
			this.lbErrors.Name = "lbErrors";
			this.lbErrors.Size = new System.Drawing.Size(608, 173);
			this.lbErrors.TabIndex = 2;
			// 
			// rtbEditor
			// 
			this.rtbEditor.Dock = System.Windows.Forms.DockStyle.Fill;
			this.rtbEditor.Location = new System.Drawing.Point(0, 28);
			this.rtbEditor.Name = "rtbEditor";
			this.rtbEditor.Size = new System.Drawing.Size(608, 236);
			this.rtbEditor.TabIndex = 1;
			this.rtbEditor.Text = "rtbEditor";
			this.rtbEditor.TextChanged += new System.EventHandler(this.rtbEditor_TextChanged);
			// 
			// imgListEditor
			// 
			this.imgListEditor.ImageSize = new System.Drawing.Size(16, 16);
			this.imgListEditor.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListEditor.ImageStream")));
			this.imgListEditor.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// tbbSave
			// 
			this.tbbSave.ImageIndex = 0;
			// 
			// tbbCompile
			// 
			this.tbbCompile.ImageIndex = 1;
			// 
			// tbbExecute
			// 
			this.tbbExecute.ImageIndex = 2;
			// 
			// Editor
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(608, 453);
			this.Controls.Add(this.EditorPanel);
			this.Name = "Editor";
			this.Text = "Editor";
			this.EditorPanel.ResumeLayout(false);
			this.panEditor.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void rtbEditor_TextChanged(object sender, System.EventArgs e)
		{
			// Calculate the starting position of the current line.
			int start = 0, end = 0;
			for (start = rtbEditor.SelectionStart - 1; start > 0; start--) 
			{
				if (rtbEditor.Text[start] == '\n') { start++; break; }
			}

			// Calculate the end position of the current line.
			for (end = rtbEditor.SelectionStart; end < rtbEditor.Text.Length; end++) 
			{
				if (rtbEditor.Text[end] == '\n') break;
			} 

			// Extract the current line that is being edited.
			String line = rtbEditor.Text.Substring(start, end - start);

			// Backup the users current selection point.
			int selectionStart = rtbEditor.SelectionStart;
			int selectionLength = rtbEditor.SelectionLength;

			// Split the line into tokens.
			Regex r = new Regex("([ \\t{}();])");
			string [] tokens = r.Split(line);
			int index = start;
			foreach (string token in tokens) 
			{
				// Set the token's default color and font.
				rtbEditor.SelectionStart = index;
				rtbEditor.SelectionLength = token.Length;
				rtbEditor.SelectionColor = Color.Black;
				rtbEditor.SelectionFont = new Font("Courier New", 10, 
					FontStyle.Regular);

				// Check whether the token is a keyword. 
				String [] keywords = { "public", "void", "using", "static", 
										 "class" }; 
				for (int i = 0; i < keywords.Length; i++) 
				{
					if (keywords[i] == token) 
					{
						// Apply alternative color and font to highlight keyword. 
						rtbEditor.SelectionColor = Color.Blue;
						rtbEditor.SelectionFont = new Font("Courier New", 10, 
							FontStyle.Bold);
						break;
					}
				}
				index += token.Length;
			}
			// Restore the users current selection point. 
			rtbEditor.SelectionStart = selectionStart;
			rtbEditor.SelectionLength = selectionLength; 
		}
/***************************************************************************/
		public static void Main(string[] args)
		{
			Application.Run(new EditorForm());
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/
