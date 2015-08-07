/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Text.RegularExpressions;
/***************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Editor.
	/// </summary>
	/***************************************************************************/
	public class EditorPanel : System.Windows.Forms.Panel
	{
/***************************************************************************/
		//private System.Windows.Forms.Panel EditorPanel;
		private System.Windows.Forms.Panel panEditor;
//		private System.Windows.Forms.ImageList imgListEditor;
		private System.ComponentModel.IContainer components;
/***************************************************************************/
		private System.Windows.Forms.RichTextBox rtbEditor;
		private System.Windows.Forms.ToolBar toolBarEditor;
		private System.Windows.Forms.ToolBarButton tbbSave;
		private System.Windows.Forms.ToolBarButton tbbCompile;
		private System.Windows.Forms.ToolBarButton tbbExecute;
		private System.Windows.Forms.ToolBarButton toolBarButton1;
		private System.Windows.Forms.ToolBarButton tbbFont;
		private System.Windows.Forms.ListBox lbErrors;
/***************************************************************************/
		private bool runTextChange = true;
		private Font editorFont;
		private String [] keywords = { "create", "replace", "from", "where", "alter", 
										"select", "update", "delete", "dbms", "output", "println",
										"procedure", "function", "trigger", "view"
									 }; 
		
/***************************************************************************/
		private string type;			// type of object being edited. ie. procedure/trigger
		public Database database;
		public string schema_name;		
		public string created_by;		//user who has created this object
		public string object_name;		//name of the object being edited
		public TabPage myTabPage;
/***************************************************************************/
		public EditorPanel(Database database, string schema, string objName, string username, 
							string type, ImageList imgList)
		{
			// Required for Windows Form Designer support
			InitializeComponent();
			/*--------------------------------------------------------------------*/
			this.type = type;
			this.database = database;
			this.schema_name = schema;
			this.object_name = objName;
			this.created_by = username;
			this.Dock = DockStyle.Fill;
			/*--------------------------------------------------------------------*/
			this.toolBarEditor.ImageList = imgList;
			this.tbbCompile.ImageIndex = MyImageList.getImgList().getImageIndex("COMPILE");
			this.tbbExecute.ImageIndex = MyImageList.getImgList().getImageIndex("EXECUTE");
			this.tbbFont.ImageIndex = MyImageList.getImgList().getImageIndex("FONT");
			this.tbbSave.ImageIndex = MyImageList.getImgList().getImageIndex("SAVE");
			/*--------------------------------------------------------------------*/
			if (type.ToUpper().Equals("TRIGGER") || type.ToUpper().Equals("VIEW"))
			{
				//do not show the execute button on the toolbar
				this.tbbExecute.Visible = false;
			}
			else
			{
				this.tbbExecute.Visible = true;
			}
			/*--------------------------------------------------------------------*/
			editorFont = this.rtbEditor.Font;
			this.rtbEditor.TextChanged += new EventHandler(rtbEditor_TextChanged);
			this.toolBarEditor.ButtonClick += new ToolBarButtonClickEventHandler(toolBarEditor_ButtonClick);
			this.rtbEditor.Text = "create or replace";
			/*--------------------------------------------------------------------*/
			this.highlightSyntax(0, this.rtbEditor.Text);
			this.rtbEditor.Select(this.rtbEditor.Text.Length, 0);
			/*--------------------------------------------------------------------*/
			runTextChange = true;
			/*--------------------------------------------------------------------*/
			this.lbErrors.Hide();
			/*--------------------------------------------------------------------*/
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
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(Form));
			this.panEditor = new System.Windows.Forms.Panel();
//			this.imgListEditor = new System.Windows.Forms.ImageList(this.components);
			this.rtbEditor = new System.Windows.Forms.RichTextBox();
			this.toolBarEditor = new System.Windows.Forms.ToolBar();
			this.tbbSave = new System.Windows.Forms.ToolBarButton();
			this.tbbCompile = new System.Windows.Forms.ToolBarButton();
			this.tbbExecute = new System.Windows.Forms.ToolBarButton();
			this.toolBarButton1 = new System.Windows.Forms.ToolBarButton();
			this.tbbFont = new System.Windows.Forms.ToolBarButton();
			this.lbErrors = new System.Windows.Forms.ListBox();
			
			this.panEditor.SuspendLayout();
			this.SuspendLayout();
			// 
			// EditorPanel
			// 
			this.BackColor = System.Drawing.SystemColors.Control;
			this.Controls.Add(this.lbErrors);
			this.Controls.Add(this.panEditor);
			this.Dock = System.Windows.Forms.DockStyle.Fill;
			this.Location = new System.Drawing.Point(0, 0);
			this.Name = "EditorPanel";
			this.Size = new System.Drawing.Size(608, 485);
			this.TabIndex = 0;
			// 
			// panEditor
			// 
			this.panEditor.BackColor = System.Drawing.SystemColors.AppWorkspace;
			this.panEditor.Controls.Add(this.rtbEditor);
			this.panEditor.Controls.Add(this.toolBarEditor);
			this.panEditor.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panEditor.Location = new System.Drawing.Point(0, 0);
			this.panEditor.Name = "panEditor";
			this.panEditor.Size = new System.Drawing.Size(608, 485);
			this.panEditor.TabIndex = 0;
			// 
			// imgListEditor
			// 
//			this.imgListEditor.ImageSize = new System.Drawing.Size(16, 16);
//			this.imgListEditor.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListEditor.ImageStream")));
//			this.imgListEditor.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// rtbEditor
			// 
			this.rtbEditor.Dock = System.Windows.Forms.DockStyle.Fill;
			this.rtbEditor.Font = new System.Drawing.Font("Courier New", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.rtbEditor.Location = new System.Drawing.Point(0, 28);
			this.rtbEditor.Name = "rtbEditor";
			this.rtbEditor.Size = new System.Drawing.Size(608, 457);
			this.rtbEditor.TabIndex = 7;
			this.rtbEditor.Text = "rtbEditor";
			// 
			// toolBarEditor
			// 
			this.toolBarEditor.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																							 this.tbbSave,
																							 this.tbbCompile,
																							 this.tbbExecute,
																							 this.toolBarButton1,
																							 this.tbbFont});
			this.toolBarEditor.DropDownArrows = true;
//			this.toolBarEditor.ImageList = this.imgListEditor;
			this.toolBarEditor.Location = new System.Drawing.Point(0, 0);
			this.toolBarEditor.Name = "toolBarEditor";
			this.toolBarEditor.ShowToolTips = true;
			this.toolBarEditor.Size = new System.Drawing.Size(608, 28);
			this.toolBarEditor.TabIndex = 6;
			// 
			// tbbSave
			// 
			this.tbbSave.ImageIndex = 0;
			this.tbbSave.Tag = "Save";
			// 
			// tbbCompile
			// 
			this.tbbCompile.ImageIndex = 1;
			this.tbbCompile.Tag = "Compile";
			// 
			// tbbExecute
			// 
			this.tbbExecute.ImageIndex = 2;
			this.tbbExecute.Tag = "Execute";
			// 
			// toolBarButton1
			// 
			this.toolBarButton1.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbFont
			// 
			this.tbbFont.ImageIndex = 3;
			this.tbbFont.Tag = "Font";
			// 
			// lbErrors
			// 
			this.lbErrors.Dock = System.Windows.Forms.DockStyle.Bottom;
			this.lbErrors.Location = new System.Drawing.Point(0, 416);
			this.lbErrors.Name = "lbErrors";
			this.lbErrors.Size = new System.Drawing.Size(608, 69);
			this.lbErrors.TabIndex = 2;
			

			this.panEditor.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		private void rtbEditor_TextChanged(object sender, System.EventArgs e)
		{
			if (this.runTextChange && this.rtbEditor.Text.Length > 0)
			{
				// Calculate the starting position of the current line.
				int start = 0, end = 0;
				if (rtbEditor.SelectionStart == 0)
				{
					start = rtbEditor.SelectionStart;
				}
				else
				{
					for (start = rtbEditor.SelectionStart - 1; start > 0; start--) 
					{
						if (rtbEditor.Text[start] == '\n') { start++; break; }
					}
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

				highlightSyntax(start, line);

				// Restore the users current selection point. 
				rtbEditor.SelectionStart = selectionStart;
				rtbEditor.SelectionLength = selectionLength; 
			}
		}
/***************************************************************************/
//		static void Main() 
//		{
//			Application.Run(new EditorForm("PROCEDURE"));
//		}
/***************************************************************************/
		private void toolBarEditor_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			ToolBarButton tbbClicked = (ToolBarButton)e.Button;
			if (tbbClicked.Tag.ToString().ToUpper().Equals("SAVE"))
			{
				//save the contents of the rich text box
			}
			else if (tbbClicked.Tag.ToString().ToUpper().Equals("COMPILE"))
			{
				//compile and give error message
			}
			else if (tbbClicked.Tag.ToString().ToUpper().Equals("EXECUTE"))
			{
				//execute the procedure
			}
			else if (tbbClicked.Tag.ToString().ToUpper().Equals("FONT"))
			{
				//show font dialog box 
				FontDialog fd = new FontDialog();
				fd.ShowColor = false;
				fd.ShowEffects = false;
				fd.ShowApply = false;
				if (fd.ShowDialog() == DialogResult.OK)
				{
					//change the rich text box font
					this.editorFont = fd.Font;
					this.rtbEditor.Font = fd.Font;
					highlightSyntax(0, this.rtbEditor.Text);
				}
				
			}
		}
/***************************************************************************/
		public void highlightSyntax(int start, string line)
		{
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
				//rtbEditor.SelectionFont = new Font("Courier New", 10, FontStyle.Regular);
				rtbEditor.SelectionFont = this.editorFont;

				// Check whether the token is a keyword. 
				for (int i = 0; i < keywords.Length; i++) 
				{
					if (keywords[i].ToUpper().Equals(token.ToUpper()))
					{
						// Apply alternative color and font to highlight keyword. 
						rtbEditor.SelectionColor = Color.Blue;
						//rtbEditor.SelectionFont = new Font("Courier New", 10, FontStyle.Bold);
						rtbEditor.SelectionFont = this.editorFont;
						break;
					}
				}
				index += token.Length;
			}
		}
/***************************************************************************/
	}
	/***************************************************************************/
}
/***************************************************************************/
