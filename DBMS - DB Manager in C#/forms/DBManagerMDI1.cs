/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using DBManager.classes;
/***************************************************************************/
namespace DBManager
{
	/// <summary>
	/// Summary description for DBManagerMDI1.
	/// </summary>
	public class DBManagerMainForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.MenuItem menuItem1;
		private System.Windows.Forms.MenuItem menuItem2;
		private System.Windows.Forms.MenuItem menuItem3;
		private System.Windows.Forms.MenuItem menuItem4;
		private System.Windows.Forms.ToolBar mainToolbar;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.ComboBox cbSchemas;
		private System.Windows.Forms.Panel panBrowser;
		private System.Windows.Forms.Splitter splitterBrowser;
		private System.Windows.Forms.ImageList imgListBrwToolbar;
		private System.Windows.Forms.Panel panConnections;
		private System.Windows.Forms.Panel panSchemaSelect;
		private System.Windows.Forms.StatusBar sbMain;
		private System.Windows.Forms.Splitter splitterMain;
		private System.Windows.Forms.ToolBar toolbarConnections;
		private System.Windows.Forms.ToolBar toolbarSchemaBrowser;
		private System.Windows.Forms.ToolBarButton tbbSchBrwClose;
		private System.Windows.Forms.ToolBarButton tbbConBrwClose;
		private System.Windows.Forms.TreeView tvDBObjects;
		private System.Windows.Forms.TreeView tvConnections;
		private System.Windows.Forms.Panel panSchemaBrowser;
		private System.Windows.Forms.ToolBarButton tbbConBrwMinRestToggle;
		private System.Windows.Forms.ToolBarButton tbbConBrwMaxRestToggle;
		private System.Windows.Forms.MenuItem miBrowser;
		private System.Windows.Forms.ImageList imgListDBObjs;
		private System.Windows.Forms.MainMenu mmBeforeLogin;
		private System.Windows.Forms.StatusBarPanel sbpanMsg;
		private System.Windows.Forms.StatusBarPanel sbpanConnection;
		private System.Windows.Forms.StatusBarPanel sbpanActiveWindow;
		private System.Windows.Forms.ImageList imgListConnections;
		private System.Windows.Forms.ContextMenu cmConnections;
		private System.Windows.Forms.ContextMenu cmWindows;
		private System.Windows.Forms.MenuItem cmiReconnect;
		private System.Windows.Forms.MenuItem cmiCloseCon;
		private System.Windows.Forms.MenuItem cmiShowWindow;
		private System.Windows.Forms.MenuItem cmiCloseWindow;
		private System.Windows.Forms.MenuItem menuItem5;
		private System.Windows.Forms.ToolBarButton tbbSchBrwMinRestToggle;
		private System.Windows.Forms.ToolBarButton tbbSchBrwMaxRestToggle;
		private System.Windows.Forms.MenuItem cmiConCloseAllWins;
		private System.Windows.Forms.TabControl tabWindows;
		private System.ComponentModel.IContainer components;
/***************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.components = new System.ComponentModel.Container();
			System.Resources.ResourceManager resources = new System.Resources.ResourceManager(typeof(DBManagerMainForm));
			this.mmBeforeLogin = new System.Windows.Forms.MainMenu();
			this.menuItem1 = new System.Windows.Forms.MenuItem();
			this.menuItem4 = new System.Windows.Forms.MenuItem();
			this.miBrowser = new System.Windows.Forms.MenuItem();
			this.menuItem2 = new System.Windows.Forms.MenuItem();
			this.menuItem3 = new System.Windows.Forms.MenuItem();
			this.mainToolbar = new System.Windows.Forms.ToolBar();
			this.sbMain = new System.Windows.Forms.StatusBar();
			this.sbpanMsg = new System.Windows.Forms.StatusBarPanel();
			this.sbpanConnection = new System.Windows.Forms.StatusBarPanel();
			this.sbpanActiveWindow = new System.Windows.Forms.StatusBarPanel();
			this.panBrowser = new System.Windows.Forms.Panel();
			this.panSchemaBrowser = new System.Windows.Forms.Panel();
			this.tvDBObjects = new System.Windows.Forms.TreeView();
			this.imgListDBObjs = new System.Windows.Forms.ImageList(this.components);
			this.panSchemaSelect = new System.Windows.Forms.Panel();
			this.cbSchemas = new System.Windows.Forms.ComboBox();
			this.label2 = new System.Windows.Forms.Label();
			this.toolbarSchemaBrowser = new System.Windows.Forms.ToolBar();
			this.tbbSchBrwMinRestToggle = new System.Windows.Forms.ToolBarButton();
			this.tbbSchBrwMaxRestToggle = new System.Windows.Forms.ToolBarButton();
			this.tbbSchBrwClose = new System.Windows.Forms.ToolBarButton();
			this.imgListBrwToolbar = new System.Windows.Forms.ImageList(this.components);
			this.splitterBrowser = new System.Windows.Forms.Splitter();
			this.panConnections = new System.Windows.Forms.Panel();
			this.tvConnections = new System.Windows.Forms.TreeView();
			this.cmConnections = new System.Windows.Forms.ContextMenu();
			this.cmiReconnect = new System.Windows.Forms.MenuItem();
			this.cmiCloseCon = new System.Windows.Forms.MenuItem();
			this.menuItem5 = new System.Windows.Forms.MenuItem();
			this.cmiConCloseAllWins = new System.Windows.Forms.MenuItem();
			this.imgListConnections = new System.Windows.Forms.ImageList(this.components);
			this.label1 = new System.Windows.Forms.Label();
			this.toolbarConnections = new System.Windows.Forms.ToolBar();
			this.tbbConBrwMinRestToggle = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwMaxRestToggle = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwClose = new System.Windows.Forms.ToolBarButton();
			this.splitterMain = new System.Windows.Forms.Splitter();
			this.cmWindows = new System.Windows.Forms.ContextMenu();
			this.cmiShowWindow = new System.Windows.Forms.MenuItem();
			this.cmiCloseWindow = new System.Windows.Forms.MenuItem();
			this.tabWindows = new System.Windows.Forms.TabControl();
			((System.ComponentModel.ISupportInitialize)(this.sbpanMsg)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanConnection)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanActiveWindow)).BeginInit();
			this.panBrowser.SuspendLayout();
			this.panSchemaBrowser.SuspendLayout();
			this.panSchemaSelect.SuspendLayout();
			this.panConnections.SuspendLayout();
			this.SuspendLayout();
			// 
			// mmBeforeLogin
			// 
			this.mmBeforeLogin.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																									this.menuItem1,
																																									this.menuItem3});
			// 
			// menuItem1
			// 
			this.menuItem1.Index = 0;
			this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																							this.menuItem4,
																																							this.miBrowser,
																																							this.menuItem2});
			this.menuItem1.Text = "File";
			// 
			// menuItem4
			// 
			this.menuItem4.Index = 0;
			this.menuItem4.Text = "New";
			this.menuItem4.Click += new System.EventHandler(this.menuItem4_Click);
			// 
			// miBrowser
			// 
			this.miBrowser.Checked = true;
			this.miBrowser.Index = 1;
			this.miBrowser.Text = "Browser";
			this.miBrowser.Click += new System.EventHandler(this.miBrowser_Click);
			// 
			// menuItem2
			// 
			this.menuItem2.Index = 2;
			this.menuItem2.Text = "Exit";
			// 
			// menuItem3
			// 
			this.menuItem3.Index = 1;
			this.menuItem3.Text = "Help";
			// 
			// mainToolbar
			// 
			this.mainToolbar.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
			this.mainToolbar.DropDownArrows = true;
			this.mainToolbar.Location = new System.Drawing.Point(0, 0);
			this.mainToolbar.Name = "mainToolbar";
			this.mainToolbar.ShowToolTips = true;
			this.mainToolbar.Size = new System.Drawing.Size(642, 44);
			this.mainToolbar.TabIndex = 1;
			// 
			// sbMain
			// 
			this.sbMain.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.sbMain.Location = new System.Drawing.Point(0, 438);
			this.sbMain.Name = "sbMain";
			this.sbMain.Panels.AddRange(new System.Windows.Forms.StatusBarPanel[] {
																																							this.sbpanMsg,
																																							this.sbpanConnection,
																																							this.sbpanActiveWindow});
			this.sbMain.ShowPanels = true;
			this.sbMain.Size = new System.Drawing.Size(642, 16);
			this.sbMain.SizingGrip = false;
			this.sbMain.TabIndex = 2;
			this.sbMain.Text = "statusBar1";
			// 
			// sbpanMsg
			// 
			this.sbpanMsg.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Spring;
			this.sbpanMsg.Text = "sbpanMsg";
			this.sbpanMsg.Width = 381;
			// 
			// sbpanConnection
			// 
			this.sbpanConnection.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Contents;
			this.sbpanConnection.Icon = ((System.Drawing.Icon)(resources.GetObject("sbpanConnection.Icon")));
			this.sbpanConnection.Text = "ActiveConnection";
			this.sbpanConnection.Width = 139;
			// 
			// sbpanActiveWindow
			// 
			this.sbpanActiveWindow.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Contents;
			this.sbpanActiveWindow.Icon = ((System.Drawing.Icon)(resources.GetObject("sbpanActiveWindow.Icon")));
			this.sbpanActiveWindow.Text = "ActiveWindow";
			this.sbpanActiveWindow.Width = 122;
			// 
			// panBrowser
			// 
			this.panBrowser.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
			this.panBrowser.Controls.Add(this.panSchemaBrowser);
			this.panBrowser.Controls.Add(this.splitterBrowser);
			this.panBrowser.Controls.Add(this.panConnections);
			this.panBrowser.Dock = System.Windows.Forms.DockStyle.Left;
			this.panBrowser.Location = new System.Drawing.Point(0, 44);
			this.panBrowser.Name = "panBrowser";
			this.panBrowser.Size = new System.Drawing.Size(264, 394);
			this.panBrowser.TabIndex = 3;
			// 
			// panSchemaBrowser
			// 
			this.panSchemaBrowser.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(192)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.panSchemaBrowser.Controls.Add(this.tvDBObjects);
			this.panSchemaBrowser.Controls.Add(this.panSchemaSelect);
			this.panSchemaBrowser.Controls.Add(this.toolbarSchemaBrowser);
			this.panSchemaBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panSchemaBrowser.Location = new System.Drawing.Point(0, 173);
			this.panSchemaBrowser.Name = "panSchemaBrowser";
			this.panSchemaBrowser.Size = new System.Drawing.Size(260, 217);
			this.panSchemaBrowser.TabIndex = 2;
			// 
			// tvDBObjects
			// 
			this.tvDBObjects.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tvDBObjects.ImageList = this.imgListDBObjs;
			this.tvDBObjects.Location = new System.Drawing.Point(0, 51);
			this.tvDBObjects.Name = "tvDBObjects";
			this.tvDBObjects.Nodes.AddRange(new System.Windows.Forms.TreeNode[] {
																																						new System.Windows.Forms.TreeNode("Database Objects", 2, 2, new System.Windows.Forms.TreeNode[] {
																																																																																							new System.Windows.Forms.TreeNode("Tables (1)", 2, 2, new System.Windows.Forms.TreeNode[] {
																																																																																																																																					new System.Windows.Forms.TreeNode("Table1", 0, 0)}),
																																																																																							new System.Windows.Forms.TreeNode("Users (2)", 2, 2, new System.Windows.Forms.TreeNode[] {
																																																																																																																																				 new System.Windows.Forms.TreeNode("user1", 1, 1),
																																																																																																																																				 new System.Windows.Forms.TreeNode("user2", 1, 1)})})});
			this.tvDBObjects.Size = new System.Drawing.Size(260, 166);
			this.tvDBObjects.TabIndex = 7;
			// 
			// imgListDBObjs
			// 
			this.imgListDBObjs.ImageSize = new System.Drawing.Size(16, 16);
			this.imgListDBObjs.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListDBObjs.ImageStream")));
			this.imgListDBObjs.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// panSchemaSelect
			// 
			this.panSchemaSelect.BackColor = System.Drawing.SystemColors.Control;
			this.panSchemaSelect.Controls.Add(this.cbSchemas);
			this.panSchemaSelect.Controls.Add(this.label2);
			this.panSchemaSelect.Dock = System.Windows.Forms.DockStyle.Top;
			this.panSchemaSelect.Location = new System.Drawing.Point(0, 29);
			this.panSchemaSelect.Name = "panSchemaSelect";
			this.panSchemaSelect.Size = new System.Drawing.Size(260, 22);
			this.panSchemaSelect.TabIndex = 6;
			// 
			// cbSchemas
			// 
			this.cbSchemas.Dock = System.Windows.Forms.DockStyle.Fill;
			this.cbSchemas.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.cbSchemas.Items.AddRange(new object[] {
																									 "ALL",
																									 "schema1",
																									 "schema2",
																									 "myschema1",
																									 "myschema2"});
			this.cbSchemas.Location = new System.Drawing.Point(72, 0);
			this.cbSchemas.Name = "cbSchemas";
			this.cbSchemas.Size = new System.Drawing.Size(188, 21);
			this.cbSchemas.TabIndex = 7;
			// 
			// label2
			// 
			this.label2.BackColor = System.Drawing.SystemColors.Control;
			this.label2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.label2.Dock = System.Windows.Forms.DockStyle.Left;
			this.label2.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label2.Location = new System.Drawing.Point(0, 0);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(72, 22);
			this.label2.TabIndex = 6;
			this.label2.Text = "Schemas:";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// toolbarSchemaBrowser
			// 
			this.toolbarSchemaBrowser.Appearance = System.Windows.Forms.ToolBarAppearance.Flat;
			this.toolbarSchemaBrowser.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.toolbarSchemaBrowser.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.toolbarSchemaBrowser.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																																														this.tbbSchBrwMinRestToggle,
																																														this.tbbSchBrwMaxRestToggle,
																																														this.tbbSchBrwClose});
			this.toolbarSchemaBrowser.ButtonSize = new System.Drawing.Size(23, 22);
			this.toolbarSchemaBrowser.DropDownArrows = true;
			this.toolbarSchemaBrowser.ImageList = this.imgListBrwToolbar;
			this.toolbarSchemaBrowser.Location = new System.Drawing.Point(0, 0);
			this.toolbarSchemaBrowser.Name = "toolbarSchemaBrowser";
			this.toolbarSchemaBrowser.ShowToolTips = true;
			this.toolbarSchemaBrowser.Size = new System.Drawing.Size(260, 29);
			this.toolbarSchemaBrowser.TabIndex = 3;
			this.toolbarSchemaBrowser.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.toolbarSchemaBrowser_ButtonClick);
			// 
			// tbbSchBrwMinRestToggle
			// 
			this.tbbSchBrwMinRestToggle.ImageIndex = 2;
			this.tbbSchBrwMinRestToggle.Tag = "SchBrwMinRestToggle";
			// 
			// tbbSchBrwMaxRestToggle
			// 
			this.tbbSchBrwMaxRestToggle.ImageIndex = 4;
			this.tbbSchBrwMaxRestToggle.Tag = "SchBrwMinRestToggle";
			// 
			// tbbSchBrwClose
			// 
			this.tbbSchBrwClose.ImageIndex = 5;
			this.tbbSchBrwClose.Tag = "SchBrwClose";
			// 
			// imgListBrwToolbar
			// 
			this.imgListBrwToolbar.ImageSize = new System.Drawing.Size(16, 16);
			this.imgListBrwToolbar.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListBrwToolbar.ImageStream")));
			this.imgListBrwToolbar.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// splitterBrowser
			// 
			this.splitterBrowser.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(192)), ((System.Byte)(192)));
			this.splitterBrowser.Dock = System.Windows.Forms.DockStyle.Top;
			this.splitterBrowser.Location = new System.Drawing.Point(0, 168);
			this.splitterBrowser.Name = "splitterBrowser";
			this.splitterBrowser.Size = new System.Drawing.Size(260, 5);
			this.splitterBrowser.TabIndex = 1;
			this.splitterBrowser.TabStop = false;
			// 
			// panConnections
			// 
			this.panConnections.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.panConnections.Controls.Add(this.tvConnections);
			this.panConnections.Controls.Add(this.label1);
			this.panConnections.Controls.Add(this.toolbarConnections);
			this.panConnections.Dock = System.Windows.Forms.DockStyle.Top;
			this.panConnections.Location = new System.Drawing.Point(0, 0);
			this.panConnections.Name = "panConnections";
			this.panConnections.Size = new System.Drawing.Size(260, 168);
			this.panConnections.TabIndex = 0;
			// 
			// tvConnections
			// 
			this.tvConnections.ContextMenu = this.cmConnections;
			this.tvConnections.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tvConnections.ImageList = this.imgListConnections;
			this.tvConnections.Location = new System.Drawing.Point(0, 45);
			this.tvConnections.Name = "tvConnections";
			this.tvConnections.Size = new System.Drawing.Size(260, 123);
			this.tvConnections.TabIndex = 5;
			this.tvConnections.MouseDown += new System.Windows.Forms.MouseEventHandler(this.tvConnections_MouseDown);
			// 
			// cmConnections
			// 
			this.cmConnections.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																									this.cmiReconnect,
																																									this.cmiCloseCon,
																																									this.menuItem5,
																																									this.cmiConCloseAllWins});
			// 
			// cmiReconnect
			// 
			this.cmiReconnect.Index = 0;
			this.cmiReconnect.Text = "Reconnect";
			// 
			// cmiCloseCon
			// 
			this.cmiCloseCon.Index = 1;
			this.cmiCloseCon.Text = "Disconnect";
			// 
			// menuItem5
			// 
			this.menuItem5.Index = 2;
			this.menuItem5.Text = "-";
			// 
			// cmiConCloseAllWins
			// 
			this.cmiConCloseAllWins.Index = 3;
			this.cmiConCloseAllWins.Text = "Close All Windows";
			this.cmiConCloseAllWins.Click += new System.EventHandler(this.cmiConCloseAllWins_Click);
			// 
			// imgListConnections
			// 
			this.imgListConnections.ImageSize = new System.Drawing.Size(16, 16);
			this.imgListConnections.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListConnections.ImageStream")));
			this.imgListConnections.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// label1
			// 
			this.label1.BackColor = System.Drawing.SystemColors.Control;
			this.label1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.label1.Dock = System.Windows.Forms.DockStyle.Top;
			this.label1.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label1.Location = new System.Drawing.Point(0, 29);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(260, 16);
			this.label1.TabIndex = 4;
			this.label1.Text = "Connections and Windows";
			// 
			// toolbarConnections
			// 
			this.toolbarConnections.Appearance = System.Windows.Forms.ToolBarAppearance.Flat;
			this.toolbarConnections.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.toolbarConnections.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.toolbarConnections.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																																													this.tbbConBrwMinRestToggle,
																																													this.tbbConBrwMaxRestToggle,
																																													this.tbbConBrwClose});
			this.toolbarConnections.ButtonSize = new System.Drawing.Size(23, 22);
			this.toolbarConnections.DropDownArrows = true;
			this.toolbarConnections.ImageList = this.imgListBrwToolbar;
			this.toolbarConnections.Location = new System.Drawing.Point(0, 0);
			this.toolbarConnections.Name = "toolbarConnections";
			this.toolbarConnections.ShowToolTips = true;
			this.toolbarConnections.Size = new System.Drawing.Size(260, 29);
			this.toolbarConnections.TabIndex = 2;
			this.toolbarConnections.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.connectionToolbar_ButtonClick);
			// 
			// tbbConBrwMinRestToggle
			// 
			this.tbbConBrwMinRestToggle.ImageIndex = 2;
			this.tbbConBrwMinRestToggle.Tag = "ConBrwMinRestToggle";
			this.tbbConBrwMinRestToggle.Visible = false;
			// 
			// tbbConBrwMaxRestToggle
			// 
			this.tbbConBrwMaxRestToggle.ImageIndex = 4;
			this.tbbConBrwMaxRestToggle.Tag = "ConBrwMaxRestToggle";
			this.tbbConBrwMaxRestToggle.Visible = false;
			// 
			// tbbConBrwClose
			// 
			this.tbbConBrwClose.ImageIndex = 5;
			this.tbbConBrwClose.Tag = "ConBrwClose";
			// 
			// splitterMain
			// 
			this.splitterMain.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(192)), ((System.Byte)(0)), ((System.Byte)(0)));
			this.splitterMain.Location = new System.Drawing.Point(264, 44);
			this.splitterMain.Name = "splitterMain";
			this.splitterMain.Size = new System.Drawing.Size(5, 394);
			this.splitterMain.TabIndex = 5;
			this.splitterMain.TabStop = false;
			// 
			// cmWindows
			// 
			this.cmWindows.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																																							this.cmiShowWindow,
																																							this.cmiCloseWindow});
			// 
			// cmiShowWindow
			// 
			this.cmiShowWindow.Index = 0;
			this.cmiShowWindow.Text = "Show Window";
			// 
			// cmiCloseWindow
			// 
			this.cmiCloseWindow.Index = 1;
			this.cmiCloseWindow.Text = "Close Window";
			// 
			// tabWindows
			// 
			this.tabWindows.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tabWindows.Location = new System.Drawing.Point(269, 44);
			this.tabWindows.Name = "tabWindows";
			this.tabWindows.SelectedIndex = 0;
			this.tabWindows.Size = new System.Drawing.Size(373, 394);
			this.tabWindows.TabIndex = 6;
			// 
			// DBManagerMainForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(642, 454);
			this.Controls.Add(this.tabWindows);
			this.Controls.Add(this.splitterMain);
			this.Controls.Add(this.panBrowser);
			this.Controls.Add(this.sbMain);
			this.Controls.Add(this.mainToolbar);
			this.Menu = this.mmBeforeLogin;
			this.Name = "DBManagerMainForm";
			this.Text = "Database Manager";
			this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
			this.Load += new System.EventHandler(this.mdiDBManager1_Load);
			((System.ComponentModel.ISupportInitialize)(this.sbpanMsg)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanConnection)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanActiveWindow)).EndInit();
			this.panBrowser.ResumeLayout(false);
			this.panSchemaBrowser.ResumeLayout(false);
			this.panSchemaSelect.ResumeLayout(false);
			this.panConnections.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		public DBManagerMainForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
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
		private void mdiDBManager1_Load(object sender, System.EventArgs e)
		{
			init_connectionsToolbar();
			init_schemaBrowserToolbar();
			init_conWinsTree();
		}
/***************************************************************************/
		private void init_conWinsTree()
		{
			this.tvConnections.Nodes.AddRange
				(new System.Windows.Forms.TreeNode []
					{
						new ConnectionTreeNode("user1@database1", 0, 0, true),
						new ConnectionTreeNode("user2@database1", 1, 1, false)
					}
				);
			
		}
/***************************************************************************/
		private void init_connectionsToolbar()
		{
//			this.toolbarConnections.Buttons[0].Tag = "ConnectionToolbarToggle";
//			this.toolbarConnections.Buttons[1].Tag = "ConnectionToolbarClose";
		}
/***************************************************************************/
		private void init_schemaBrowserToolbar()
		{
//			this.toolbarSchemaBrowser.Buttons[0].Tag = "SchemaBrowserToggle";
//			this.toolbarSchemaBrowser.Buttons[1].Tag = "SchemaBrowserClose";
		}
/***************************************************************************/
		static void Main() 
		{
			Application.Run(new DBManagerMainForm());
		}
/***************************************************************************/
		private void menuItem4_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode activeConNode;
			TreeNode selNode = this.tvConnections.SelectedNode;
			if (selNode != null)
			{
				//MessageBox.Show(selNode.GetType().ToString());
				if (selNode.GetType().ToString().Equals("DBManager.classes.ConnectionTreeNode"))
				{
					activeConNode = (ConnectionTreeNode)this.tvConnections.SelectedNode;
				}
				else	//selected node is a window node, so get its parent
				{
					activeConNode = (ConnectionTreeNode)this.tvConnections.SelectedNode.Parent;
				}
				if (!activeConNode.isConnected)
				{
					MessageBox.Show("The database connection is lost. Please Reconnect and try again.");
				}
				else //connection is active. allow user to open any new window
				{
					this.tabWindows.TabPages.Add(new TabPage("SQL Window"));
//					int FormCount = 0;
//					Form1 frmTemp=new Form1();
//					frmTemp.MdiParent=this;
//					activeConNode.addWindow(frmTemp);
//					frmTemp.Text="Window#" + FormCount.ToString();
//					FormCount++;
//					frmTemp.Show();
				}
			}
			else
			{
				MessageBox.Show("Please select an active database connection and try again.");
			}
		}
/***************************************************************************/
		private void connectionToolbar_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			ToolBar tb = (ToolBar)sender;
			ToolBarButton tbb = (ToolBarButton)e.Button;
			string strBtn = (string)tbb.Tag;
			//MessageBox.Show(strBtn);
			switch (strBtn)
			{
				case "ConBrwMinRestToggle" : change_toolbars(this.toolbarConnections, tbb, this.panConnections); break;
				case "ConBrwMaxRestToggle" : change_toolbars(this.toolbarConnections, tbb, this.panConnections); break;
				case "ConBrwClose"  : this.panBrowser.Hide(); 
															this.splitterMain.Hide();
															this.miBrowser.Checked = false;
															break;
			};
		}
/***************************************************************************/
		const int MINIMIZE = 2;
		const int RESTORE = 3;
		const int MAXIMIZE = 4;
		const int CLOSE = 5;
/***************************************************************************/
		private void toolbarSchemaBrowser_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
//			ToolBar tb = (ToolBar)sender;
//			ToolBarButton tbb = (ToolBarButton)e.Button;
//			string strBtn = (string)tbb.Tag;
//			//MessageBox.Show(strBtn);
//			switch (strBtn)
//			{
//				case "SchBrwMinRestToggle" : change_toolbars(this.toolbarSchemaBrowser, tbb, this.panSchemaBrowser); break;
//				case "SchBrwMaxRestToggle" : change_toolbars(this.toolbarSchemaBrowser, tbb, this.panSchemaBrowser); break;
//				case "SchBrwClose"  : break;
//			};
		}
/***************************************************************************/
		/*
		 * First parameter is the Toolbar that is clicked.
		 * Second parameter is the Toolbar button that is clicked
		 * Third paramter is the Panel which is to be resized
		*/
		private void change_toolbars(ToolBar tbClicked, ToolBarButton tbbClicked, Panel pan)
		{
			string strTag = (string)tbbClicked.Tag;
			switch (strTag)
			{
				case "SchBrwMinRestToggle" : 
				case "ConBrwMinRestToggle" : 
					if (tbbClicked.ImageIndex == MINIMIZE)
					{
						if (pan == this.panConnections)
						{
							pan.Size = new Size(0,this.toolbarConnections.Height);
						}
						else
						{
							this.panConnections.Size = new Size(0, this.panBrowser.Size.Height - 
																											this.toolbarSchemaBrowser.Height - 
																											this.splitterBrowser.Height);
						}
						tbbClicked.ImageIndex = RESTORE;
						//restore / maximize toggle button should show maximize
						for (int i=0; i < tbClicked.Buttons.Count; i++)
						{
							if (tbClicked.Buttons[i].Tag.ToString().EndsWith("MaxRestToggle"))
							{
								tbClicked.Buttons[i].ImageIndex = MAXIMIZE;
							}
						}
					}
					else if (tbbClicked.ImageIndex == RESTORE)
					{
						if (pan == this.panConnections)
						{
							pan.Size = new Size(0,this.toolbarConnections.Height);
						}
						else
						{
							this.panConnections.Size = new Size(0, this.panBrowser.Size.Height - 
								this.toolbarSchemaBrowser.Height - 
								this.splitterBrowser.Height);
						}
						tbbClicked.ImageIndex = MINIMIZE;
						pan.Size = new Size(0, this.panBrowser.Height/3);
					}
					break;
				//-----------------------------------------------------------------------
				case "SchBrwMaxRestToggle" : 
				case "ConBrwMaxRestToggle" : 
					if (tbbClicked.ImageIndex == RESTORE)
					{
						tbbClicked.ImageIndex = MAXIMIZE;
					}
					else if (tbbClicked.ImageIndex == MAXIMIZE)
					{
						tbbClicked.ImageIndex = RESTORE;
						//minimize / restore toggle button should show minimize
						for (int i=0; i < tbClicked.Buttons.Count; i++)
						{
							if (tbClicked.Buttons[i].Tag.ToString().EndsWith("MinRestToggle"))
							{
								tbClicked.Buttons[i].ImageIndex = MINIMIZE;
							}
						}
					}
					break;
			};
		}
/***************************************************************************/
		private void miBrowser_Click(object sender, System.EventArgs e)
		{
			MenuItem mi = (MenuItem)sender;
			mi.Checked ^= true;
			if (mi.Checked)
			{
				this.panBrowser.Show();
				this.splitterMain.Show();
			}
			else
			{
				this.panBrowser.Hide();
				this.splitterMain.Hide();
			}
		}
/***************************************************************************/
		private void tvConnections_MouseDown(object sender, MouseEventArgs mea)
		{
			TreeView tvCon = (TreeView)sender;
			TreeNode tn = tvCon.GetNodeAt(mea.X, mea.Y);
			if (tn != null && mea.Button == MouseButtons.Right)
			{
				tnRightClicked = tn;
				if (tn.Text.IndexOf("@") >= 0)
				{
					this.tvConnections.ContextMenu = this.cmConnections;
					//cmConnections.Show();
				}
				else
				{
					this.tvConnections.ContextMenu = this.cmWindows;
				}
				this.sbpanMsg.Text =  tn.Text;
				//this.sbMain.Invalidate();
			}
			else
			{
				this.tvConnections.ContextMenu = null;
			}
		}
/***************************************************************************/
		private TreeNode tnRightClicked;
/***************************************************************************/
		private void cmiConCloseAllWins_Click(object sender, System.EventArgs e)
		{
			MessageBox.Show(this.tnRightClicked.ToString());
		}
/***************************************************************************/
	}
/***************************************************************************/
}
