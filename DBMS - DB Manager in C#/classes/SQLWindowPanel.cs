/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using DBManager.classes;
using System.Text.RegularExpressions;
/***************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for SQLWindowPanel.
	/// </summary>
/***************************************************************************/
	public class SQLWindowPanel : Panel
	{
/***************************************************************************/
		public DBManager.classes.SqlQueries SqlQueryObj = new DBManager.classes.SqlQueries();
		private System.Windows.Forms.Panel panSQL;
		private System.Windows.Forms.Splitter splitter2;
		private System.Windows.Forms.DataGrid grdSqlOutput;
		private System.Windows.Forms.RichTextBox txtSqlWindow;
		private System.Windows.Forms.ToolBar sqlWindowToolbar;
		private System.Windows.Forms.ToolBarButton prevSqlButton;
		private System.Windows.Forms.ToolBarButton nextSqlButton;
		private System.Windows.Forms.ToolBarButton execSqlButton;
		

		public string windowName;
		public string schema_name;
		public Database database;
		public TabPage myTabPage;

		//for procedure editor
		//		private System.Windows.Forms.Panel sqlViewPanel;
		//		private System.Windows.Forms.Panel sqlEditViewPanel;
		//		private System.Windows.Forms.Splitter sqlViewWindowSplitter;
		//		private System.Windows.Forms.ToolBar tbbSqlView;
		//		private System.Windows.Forms.ToolBarButton tbbSqlCompile;
		//		private System.Windows.Forms.RichTextBox txtSqlView;
		//		private System.Windows.Forms.TabControl tab;
		//		private System.Windows.Forms.TabPage tabSqlWindow;
		//		private System.Windows.Forms.TabPage tabViewSql;
		//		private System.Windows.Forms.ListBox lstErrorView;
		public bool flag=true;		
/***************************************************************************/
		public SQLWindowPanel(Database database, string schema, string windowName)
		{
			InitializeComponent();
			this.database = database;
			this.schema_name = schema;
			this.windowName = windowName;

			this.Dock = DockStyle.Fill;
			this.txtSqlWindow.TextChanged += new System.EventHandler(this.txtSqlWindow_TextChanged);
			this.sqlWindowToolbar.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.sqlWindowToolbar_ButtonClick);
			
		}
/***************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.panSQL = new System.Windows.Forms.Panel();
			this.splitter2 = new System.Windows.Forms.Splitter();
			this.sqlWindowToolbar = new System.Windows.Forms.ToolBar();
			this.grdSqlOutput = new System.Windows.Forms.DataGrid();
			this.prevSqlButton = new System.Windows.Forms.ToolBarButton();
			this.nextSqlButton = new System.Windows.Forms.ToolBarButton();
			this.execSqlButton = new System.Windows.Forms.ToolBarButton();
			this.txtSqlWindow = new System.Windows.Forms.RichTextBox();
			
			this.panSQL.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.grdSqlOutput)).BeginInit();
			this.SuspendLayout();
			// 
			// SQLWindowPanel
			// 
			this.Controls.Add(this.grdSqlOutput);
			this.Controls.Add(this.splitter2);
			this.Controls.Add(this.panSQL);
			this.Dock = System.Windows.Forms.DockStyle.Fill;
			this.Location = new System.Drawing.Point(0, 0);
			this.Name = "SQLWindowPanel";
			this.Size = new System.Drawing.Size(568, 429);
			this.TabIndex = 0;
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
			this.splitter2.Size = new System.Drawing.Size(568, 5);
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
			this.sqlWindowToolbar.Enabled=false;
			// 
			// grdSqlOutput
			// 
			this.grdSqlOutput.DataMember = "";
			this.grdSqlOutput.CaptionText="Output";
			this.grdSqlOutput.Dock = System.Windows.Forms.DockStyle.Fill;
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
			this.txtSqlWindow.Text = "";
			//this.txtSqlWindow.Font=""
			
            this.ResumeLayout(false);
			this.panSQL.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.grdSqlOutput)).EndInit();
			this.ResumeLayout(false);
		}
		#endregion
/***************************************************************************/
		private void txtSqlWindow_TextChanged(object sender, System.EventArgs e)
		{
			RichTextBox tbox = (RichTextBox)sender;
			this.sqlWindowToolbar.Enabled=true;	
			if (tbox.Text.Length > 0)
			{
				if (!tbox.Text.Trim().Equals(""))
				{
					for (int i=0;i<sqlWindowToolbar.Buttons.Count;i++)
					{
						if (SqlQueryObj.getMarker() > 0)
						{
							if (tbox.Text.Length > 0)
							{
								sqlWindowToolbar.Buttons[i].Enabled=true;
							}
							else
							{
								if (i>0)
								{
									sqlWindowToolbar.Buttons[i].Enabled=false;
								}
								else
								{
									//sqlWindowToolbar.Buttons[0].ImageIndex=16
									sqlWindowToolbar.Buttons[0].Enabled=true;
								}
							}
						}
						else
						{
							if (i>0)
							{
								sqlWindowToolbar.Buttons[i].Enabled=true;
							}
							else
							{
								sqlWindowToolbar.Buttons[0].Enabled=false;
							}
						}
					}
				}
				else
				{
					for (int i=0;i<sqlWindowToolbar.Buttons.Count;i++)
					{
						sqlWindowToolbar.Buttons[i].Enabled=false;
					}
					//sqlWindowToolbar.Enabled=false;	
				}
			}
			else
			{
				for (int i=0;i<sqlWindowToolbar.Buttons.Count;i++)
				{
					if (SqlQueryObj.getMarker() > 0)
					{
						if (i>0)
						{
							sqlWindowToolbar.Buttons[i].Enabled=false;
						}
						else
						{
							sqlWindowToolbar.Buttons[0].Enabled=true;
						}
					}
					else
					{
						sqlWindowToolbar.Buttons[i].Enabled=false;
					}
				}
				//sqlWindowToolbar.Enabled=false;	
			}
			Parse(tbox);
		}
		/***************************************************************************/
		void Parse(RichTextBox sqlTextBox) 
		{
			String inputLanguage = sqlTextBox.Text;
			sqlTextBox.Text="";
			ParseLine(sqlTextBox,inputLanguage);
		}
		/***************************************************************************/
		void ParseLine(RichTextBox sqlTextBox,string line) 
		{
			Regex r = new Regex("([ \\t{}();])");
			String [] tokens = r.Split(line);

			foreach (string token in tokens) 
			{
				// Set the token's default color and font.
				sqlTextBox.SelectionColor = Color.Black;

				// Check for a comment.
				if (token == "--" || token.StartsWith("--")) 
				{
					// Find the start of the comment and then extract the whole comment.
					int index = line.IndexOf("--");
					string comment = line.Substring(index, line.Length - index);
					sqlTextBox.SelectionColor = Color.Green;
					sqlTextBox.SelectedText = comment;
					break;
				}
				/*
				if (token == "'" || token.StartsWith("'")) 
				{
					// Find the start of the comment and then extract the whole comment.
					int index = line.IndexOf("'");
					string comment1 = line.Substring(index, line.Length - index);
					if (flag)
					{
						sqlTextBox.SelectionColor = Color.Red;
						flag=false;
					}
					else
					{
						flag=true;
					}
					sqlTextBox.SelectedText = comment1;
					break;
				}
				*/      
				// Check whether the token is a keyword. 
				String [] keywords = { "select", "from", "where", "like", "and" , "or" }; 
				for (int i = 0; i < keywords.Length; i++) 
				{
					if (keywords[i].ToLower().Equals(token.ToLower()))
					{
						// Apply alternative color and font to highlight keyword.
						sqlTextBox.SelectionColor = Color.Blue;
						break;
					}
				}
				sqlTextBox.SelectedText = token;
			}
		}
		/***************************************************************************/
		private void sqlWindowToolbar_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			ToolBar sqlTb = (ToolBar)sender;
			ToolBarButton sqlTbb = (ToolBarButton)e.Button;

			string strSqlBtn = (string)sqlTbb.Tag;
			
			switch (strSqlBtn)
			{
				case "1" : 
					SqlQueryObj.setPrev();
					if (SqlQueryObj.getMarker() >=0 )
					{
						txtSqlWindow.Text=SqlQueryObj.getQuery(SqlQueryObj.getMarker());
					}
					break;
				case "2" : 
					SqlQueryObj.setNext();
					if ( SqlQueryObj.getQuery(SqlQueryObj.getMarker()) != null )
					{
						if (!SqlQueryObj.getQuery(SqlQueryObj.getMarker()).Equals(""))
						{
							txtSqlWindow.Text=SqlQueryObj.getQuery(SqlQueryObj.getMarker());
						}
						else
						{
							txtSqlWindow.Text="";
						}
					}
					else
					{
						txtSqlWindow.Text="";
					}
					break;
				case "3" : 
					if(SqlQueryObj.checkSpace() == 0)
					{
						SqlQueryObj.setQuery(txtSqlWindow.Text);
						SqlQueryObj.parseSqlQuery(txtSqlWindow.Text);
						/*****************     Get columns **********************/
						ArrayList columnName = SqlQueryObj.getSqlColumns();
						for (int i=0;i<columnName.Count;i++)
						{
							string column_Name = columnName[i].ToString();
							string columnName1="";
							bool comma_flag=false;
							int index = column_Name.IndexOf(",");
							if(index>0)
							{
								columnName1 = column_Name.Substring(0,index);
								comma_flag=true;
							}
							else
							{
								comma_flag=false;
							}
							if(comma_flag)
							{
								MessageBox.Show(columnName1);
							}
							else
							{
								bool comma_startEnd = false;
								char[] MyChar = {'(',')','!',' ',','};
								string NewString = column_Name.TrimStart(MyChar);
								if(NewString.Length>0)
								{
									MessageBox.Show(NewString);
									comma_startEnd = true;
								}
								else
								{
									comma_startEnd = true;
								}
								if(!comma_startEnd)
								{
									MessageBox.Show(column_Name);
								}
							}
						}
						/*****************     Get tables **********************/
						ArrayList tableName = SqlQueryObj.getSqlTables();
						for (int i=0;i<tableName.Count;i++)
						{
							string table_Name = tableName[i].ToString();
							string tableName1="";
							bool comma_flag=false;
							int index = table_Name.IndexOf(",");
							if(index>0)
							{
								tableName1 = table_Name.Substring(0,index);
								comma_flag=true;
							}
							else
							{
								comma_flag=false;
							}
							if(comma_flag)
							{
								MessageBox.Show(tableName1);
							}
							else
							{
								bool comma_startEnd = false;
								char[] MyChar = {'(',')','!',' ',','};
								string NewString = table_Name.TrimStart(MyChar);
								if(NewString.Length>0)
								{
									MessageBox.Show(NewString);
									comma_startEnd = true;
								}
								else
								{
									comma_startEnd = true;
								}
								if(!comma_startEnd)
								{
									MessageBox.Show(table_Name);
								}
							}
						}
						/*****************     Get where conditions **********************/
						ArrayList whereCondition = SqlQueryObj.getSqlWhereConditions();
						for (int i=0;i<whereCondition.Count;i++)
						{
							string where_Condition = whereCondition[i].ToString();
							string whereCondition1="";
							bool comma_flag=false;
							int index = where_Condition.IndexOf(",");
							if(index>0)
							{
								whereCondition1 = where_Condition.Substring(0,index);
								comma_flag=true;
							}
							else
							{
								comma_flag=false;
							}
							if(comma_flag)
							{
								MessageBox.Show(whereCondition1);
							}
							else
							{
								bool comma_startEnd = false;
								char[] MyChar = {'(',')','!',' ',','};
								string NewString = where_Condition.TrimStart(MyChar);
								if(NewString.Length>0)
								{
									MessageBox.Show(NewString);
									comma_startEnd = true;
								}
								else
								{
									comma_startEnd = true;
								}
								if(!comma_startEnd)
								{
									MessageBox.Show(where_Condition);
								}
							}
						}
					}
					break;
			};
		}
/***************************************************************************/
		//for procedure editor
//		private void tbbSqlView_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
//		{
//			ToolBar sqlTb = (ToolBar)sender;
//			ToolBarButton sqlTbb = (ToolBarButton)e.Button;
//			string strSqlBtn = (string)sqlTbb.Tag;
//			
//			switch (strSqlBtn)
//			{
//				case "1" :
//					MessageBox.Show("SELECT Product, Quantity \\n FROM Inventory \\n WHERE Warehouse = 'FL'");
//						txtSqlView.Text="SELECT Product, Quantity \\nFROM Inventory \\n WHERE Warehouse = 'FL'";
//					break;
//			}
//		}
/***************************************************************************/
	}
}
/***************************************************************************/