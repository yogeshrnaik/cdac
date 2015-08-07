/***************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using DBManager.classes;
/***************************************************************************/
namespace DBManager.forms
{
	/// <summary>
	/// Summary description for DBManagerMDI1.
	/// </summary>
	public class DBManagerMainForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.MenuItem menuItem1;
		private System.Windows.Forms.MenuItem menuItem3;
		public System.Windows.Forms.Panel panBrowser;
		private System.Windows.Forms.Splitter splitterBrowser;
		public System.Windows.Forms.Panel panConnections;
		private System.Windows.Forms.StatusBar sbMain;
		private System.Windows.Forms.Splitter splitterMain;
		public System.Windows.Forms.Panel panSchemaBrowser;
		private System.Windows.Forms.StatusBarPanel sbpanMsg;
		private System.Windows.Forms.StatusBarPanel sbpanConnection;
		public System.Windows.Forms.StatusBarPanel sbpanActiveWindow;
		private System.Windows.Forms.ContextMenu cmConnections;
		private System.Windows.Forms.ContextMenu cmWindows;
		private System.Windows.Forms.MenuItem cmiReconnect;
		private System.Windows.Forms.MenuItem cmiShowWindow;
		private System.Windows.Forms.MenuItem cmiCloseWindow;
		private System.Windows.Forms.MenuItem menuItem5;
		private System.Windows.Forms.MenuItem cmiConCloseAllWins;
		private System.Windows.Forms.TabControl tabWindows;
		private System.Windows.Forms.MenuItem menuItem7;
		private System.Windows.Forms.MenuItem menuItem8;
		private System.Windows.Forms.MenuItem miNewCon;
		private System.Windows.Forms.MenuItem miConHist;
		private System.Windows.Forms.MenuItem miExit;
		private System.Windows.Forms.MenuItem miTnsnames;
		private System.Windows.Forms.MenuItem miBrowser;
		private System.Windows.Forms.MenuItem miHelpContents;
		private System.Windows.Forms.MenuItem miHelpIndex;
		private System.Windows.Forms.MenuItem miHelpAbout;
		private System.Windows.Forms.ToolBarButton tbbNewCon;
		private System.Windows.Forms.ImageList imgListMain;
		private System.Windows.Forms.ToolBarButton tbbConHist;
		private System.Windows.Forms.ToolBarButton tbbSqlWin;
		private System.Windows.Forms.ToolBarButton tbbBrowser;
		private System.Windows.Forms.ToolBarButton tbbCommit;
		private System.Windows.Forms.ToolBarButton tbbRollback;
		private System.Windows.Forms.ToolBarButton tbbFindDBObj;
		private System.Windows.Forms.ToolBarButton tbbHelp;
		private System.Windows.Forms.ContextMenu cmDBObjs;
		private System.Windows.Forms.MenuItem menuItem4;
		private System.Windows.Forms.MenuItem menuItem6;
		private System.Windows.Forms.ContextMenu cmDBObj;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Panel panBrowserTop;
		private System.Windows.Forms.Button btnCloseBrw;
		private System.Windows.Forms.Panel panSchemaSelect;
		private System.Windows.Forms.ComboBox cbSchemas;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.ToolBar toolbarSchemaBrowser;
		public System.Windows.Forms.TreeView tvDBObjects;
		private System.Windows.Forms.ToolBarButton tbbSchBrwRefresh;
		private System.Windows.Forms.ToolBarButton tbbSchBrwExpandAll;
		private System.Windows.Forms.ToolBarButton tbbSchBrwCollapseAll;
		private System.Windows.Forms.ToolBar toolbarConnections;
		private System.Windows.Forms.ToolBarButton tbbConBrwRefresh;
		private System.Windows.Forms.ToolBarButton tbbConBrwExpandAll;
		private System.Windows.Forms.ToolBarButton tbbConBrwCollapseAll;
		private System.Windows.Forms.ToolBarButton tbbConBrwMinRestToggle;
		private System.Windows.Forms.ToolBarButton tbbConBrwClose;
		public System.Windows.Forms.TreeView tvConnections;
		private System.Windows.Forms.ToolBarButton tbbSeparator1;
		private System.Windows.Forms.ToolBarButton tbbSeparator2;
		private System.Windows.Forms.ToolBarButton tbbSeparator4;
		private System.Windows.Forms.ToolBarButton tbbSeparator5;
		private System.Windows.Forms.ToolBar toolbarMain;
		private System.Windows.Forms.MenuItem miSqlnet;
		private System.Windows.Forms.MenuItem menuItem28;
		private System.Windows.Forms.MainMenu mmMainMenu;
		private System.Windows.Forms.MenuItem menuItem32;
		private System.Windows.Forms.MenuItem menuItem33;
		private System.Windows.Forms.MenuItem menuItem39;
		private System.Windows.Forms.MenuItem menuItem40;
		private System.Windows.Forms.MenuItem menuItem42;
		private System.Windows.Forms.MenuItem menuItem43;
		private System.Windows.Forms.MenuItem menuItem47;
		private System.Windows.Forms.MenuItem menuItem51;
		private System.Windows.Forms.MenuItem menuItem53;
		private System.Windows.Forms.MenuItem menuItem56;
		private System.Windows.Forms.MenuItem menuItem64;
		private System.Windows.Forms.ToolBarButton tbbSeparator3;
		private System.Windows.Forms.ToolBarButton tbbSeparator6;
		private System.Windows.Forms.MenuItem cmiDBObjsExpand;
		private System.Windows.Forms.MenuItem cmiDBObjsCollapse;
		private System.Windows.Forms.MenuItem cmiConExpand;
		private System.Windows.Forms.MenuItem cmiConCollapse;
		private System.Windows.Forms.MenuItem menuItem67;
		private System.ComponentModel.IContainer components;
		private System.Windows.Forms.MenuItem miSQLWindow;
		private System.Resources.ResourceManager resources;
		public ConnectionHistoryForm conHistForm;
		private SqlnetForm frmSqlnet;
		private TnsoraForm frmTnsora;
		private System.Windows.Forms.MenuItem miSearch;
		private System.Windows.Forms.MenuItem miCreate;
		private System.Windows.Forms.MenuItem miObjects;
		private System.Windows.Forms.MenuItem miTransaction;
		private System.Windows.Forms.MenuItem cmiDisconnect;
		private System.Windows.Forms.MenuItem cmiNewDBObj;
		private System.Windows.Forms.MenuItem cmiEditDBObj;
		private System.Windows.Forms.MenuItem menuItem25;
		private System.Windows.Forms.MenuItem cmiDropDBObj;
		private System.Windows.Forms.MenuItem miFindTable;
		private System.Windows.Forms.MenuItem miFindView;
		private System.Windows.Forms.MenuItem miFindSequence;
		private System.Windows.Forms.MenuItem miFindTrigger;
		private System.Windows.Forms.MenuItem miFindProcedure;
		private System.Windows.Forms.MenuItem miFindInProcCode;
		private System.Windows.Forms.MenuItem miFindFunction;
		private System.Windows.Forms.MenuItem miFindInFunctCode;
		private System.Windows.Forms.MenuItem miNewSequence;
		private System.Windows.Forms.MenuItem miNewFunction;
		private System.Windows.Forms.MenuItem miNewProcedure;
		private System.Windows.Forms.MenuItem miNewView;
		private System.Windows.Forms.MenuItem miNewTrigger;
		private System.Windows.Forms.MenuItem miNewUser;
		private System.Windows.Forms.MenuItem miObjTable;
		private System.Windows.Forms.MenuItem miObjSequence;
		private System.Windows.Forms.MenuItem miObjFunction;
		private System.Windows.Forms.MenuItem miObjProcedure;
		private System.Windows.Forms.MenuItem miObjView;
		private System.Windows.Forms.MenuItem miObjDBLink;
		private System.Windows.Forms.MenuItem miObjTrigger;
		private System.Windows.Forms.MenuItem miObjUser;
		private System.Windows.Forms.MenuItem miNewTable;
		private System.Windows.Forms.MenuItem miNewDBLink;
		private System.Windows.Forms.MenuItem miEditDBLink;
		private System.Windows.Forms.MenuItem miDropDBLink;
		private System.Windows.Forms.MenuItem miEditFunction;
		private System.Windows.Forms.MenuItem miDropFunction;
		private System.Windows.Forms.MenuItem miEditProcedure;
		private System.Windows.Forms.MenuItem miDropProcedure;
		private System.Windows.Forms.MenuItem miEditSequence;
		private System.Windows.Forms.MenuItem miDropSequence;
		private System.Windows.Forms.MenuItem cmiDropAll;
		private System.Windows.Forms.MenuItem miEditView;
		private System.Windows.Forms.MenuItem miDropView;
		private System.Windows.Forms.MenuItem miEditTrigger;
		private System.Windows.Forms.MenuItem miDropTrigger;
		private System.Windows.Forms.MenuItem menuItem9;
		private System.Windows.Forms.MenuItem menuItem10;
		private System.Windows.Forms.MenuItem menuItem12;
		private System.Windows.Forms.MenuItem miFindDBLink;
		private System.Windows.Forms.MenuItem miFindInTriggerCode;
		private System.Windows.Forms.MenuItem miFindInViewCode;
		private System.Windows.Forms.MenuItem miFindAllObjs;
		private System.Windows.Forms.MenuItem miFindUser;
		private System.Windows.Forms.MenuItem miEditTable;
		private System.Windows.Forms.MenuItem miDropTable;
		private System.Windows.Forms.MenuItem miDropUser;
		private System.Windows.Forms.MenuItem miEditUser;
		public ValidDatabasesInfo validDBs;
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
			this.mmMainMenu = new System.Windows.Forms.MainMenu();
			this.menuItem1 = new System.Windows.Forms.MenuItem();
			this.miNewCon = new System.Windows.Forms.MenuItem();
			this.miConHist = new System.Windows.Forms.MenuItem();
			this.menuItem7 = new System.Windows.Forms.MenuItem();
			this.miBrowser = new System.Windows.Forms.MenuItem();
			this.miSQLWindow = new System.Windows.Forms.MenuItem();
			this.menuItem28 = new System.Windows.Forms.MenuItem();
			this.miSqlnet = new System.Windows.Forms.MenuItem();
			this.miTnsnames = new System.Windows.Forms.MenuItem();
			this.menuItem8 = new System.Windows.Forms.MenuItem();
			this.miExit = new System.Windows.Forms.MenuItem();
			this.miSearch = new System.Windows.Forms.MenuItem();
			this.miFindAllObjs = new System.Windows.Forms.MenuItem();
			this.miFindDBLink = new System.Windows.Forms.MenuItem();
			this.miFindSequence = new System.Windows.Forms.MenuItem();
			this.miFindTable = new System.Windows.Forms.MenuItem();
			this.miFindUser = new System.Windows.Forms.MenuItem();
			this.menuItem10 = new System.Windows.Forms.MenuItem();
			this.miFindFunction = new System.Windows.Forms.MenuItem();
			this.miFindInFunctCode = new System.Windows.Forms.MenuItem();
			this.menuItem25 = new System.Windows.Forms.MenuItem();
			this.miFindProcedure = new System.Windows.Forms.MenuItem();
			this.miFindInProcCode = new System.Windows.Forms.MenuItem();
			this.menuItem9 = new System.Windows.Forms.MenuItem();
			this.miFindTrigger = new System.Windows.Forms.MenuItem();
			this.miFindInTriggerCode = new System.Windows.Forms.MenuItem();
			this.menuItem12 = new System.Windows.Forms.MenuItem();
			this.miFindView = new System.Windows.Forms.MenuItem();
			this.miFindInViewCode = new System.Windows.Forms.MenuItem();
			this.miCreate = new System.Windows.Forms.MenuItem();
			this.miNewDBLink = new System.Windows.Forms.MenuItem();
			this.miNewFunction = new System.Windows.Forms.MenuItem();
			this.miNewProcedure = new System.Windows.Forms.MenuItem();
			this.miNewSequence = new System.Windows.Forms.MenuItem();
			this.miNewTable = new System.Windows.Forms.MenuItem();
			this.miNewTrigger = new System.Windows.Forms.MenuItem();
			this.miNewUser = new System.Windows.Forms.MenuItem();
			this.miNewView = new System.Windows.Forms.MenuItem();
			this.miObjects = new System.Windows.Forms.MenuItem();
			this.miObjDBLink = new System.Windows.Forms.MenuItem();
			this.miEditDBLink = new System.Windows.Forms.MenuItem();
			this.miDropDBLink = new System.Windows.Forms.MenuItem();
			this.miObjFunction = new System.Windows.Forms.MenuItem();
			this.miEditFunction = new System.Windows.Forms.MenuItem();
			this.miDropFunction = new System.Windows.Forms.MenuItem();
			this.menuItem51 = new System.Windows.Forms.MenuItem();
			this.miObjProcedure = new System.Windows.Forms.MenuItem();
			this.miEditProcedure = new System.Windows.Forms.MenuItem();
			this.miDropProcedure = new System.Windows.Forms.MenuItem();
			this.menuItem53 = new System.Windows.Forms.MenuItem();
			this.miObjSequence = new System.Windows.Forms.MenuItem();
			this.miEditSequence = new System.Windows.Forms.MenuItem();
			this.miDropSequence = new System.Windows.Forms.MenuItem();
			this.menuItem47 = new System.Windows.Forms.MenuItem();
			this.miObjTable = new System.Windows.Forms.MenuItem();
			this.menuItem39 = new System.Windows.Forms.MenuItem();
			this.menuItem40 = new System.Windows.Forms.MenuItem();
			this.miEditTable = new System.Windows.Forms.MenuItem();
			this.menuItem42 = new System.Windows.Forms.MenuItem();
			this.menuItem43 = new System.Windows.Forms.MenuItem();
			this.miDropTable = new System.Windows.Forms.MenuItem();
			this.miObjTrigger = new System.Windows.Forms.MenuItem();
			this.miEditTrigger = new System.Windows.Forms.MenuItem();
			this.miDropTrigger = new System.Windows.Forms.MenuItem();
			this.menuItem56 = new System.Windows.Forms.MenuItem();
			this.miObjUser = new System.Windows.Forms.MenuItem();
			this.miEditUser = new System.Windows.Forms.MenuItem();
			this.miDropUser = new System.Windows.Forms.MenuItem();
			this.miObjView = new System.Windows.Forms.MenuItem();
			this.miEditView = new System.Windows.Forms.MenuItem();
			this.miDropView = new System.Windows.Forms.MenuItem();
			this.miTransaction = new System.Windows.Forms.MenuItem();
			this.menuItem32 = new System.Windows.Forms.MenuItem();
			this.menuItem33 = new System.Windows.Forms.MenuItem();
			this.menuItem3 = new System.Windows.Forms.MenuItem();
			this.miHelpContents = new System.Windows.Forms.MenuItem();
			this.miHelpIndex = new System.Windows.Forms.MenuItem();
			this.miHelpAbout = new System.Windows.Forms.MenuItem();
			this.toolbarMain = new System.Windows.Forms.ToolBar();
			this.tbbNewCon = new System.Windows.Forms.ToolBarButton();
			this.tbbConHist = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator1 = new System.Windows.Forms.ToolBarButton();
			this.tbbSqlWin = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator2 = new System.Windows.Forms.ToolBarButton();
			this.tbbBrowser = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator3 = new System.Windows.Forms.ToolBarButton();
			this.tbbCommit = new System.Windows.Forms.ToolBarButton();
			this.tbbRollback = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator4 = new System.Windows.Forms.ToolBarButton();
			this.tbbFindDBObj = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator5 = new System.Windows.Forms.ToolBarButton();
			this.tbbHelp = new System.Windows.Forms.ToolBarButton();
			this.tbbSeparator6 = new System.Windows.Forms.ToolBarButton();
			this.imgListMain = new System.Windows.Forms.ImageList(this.components);
			this.sbMain = new System.Windows.Forms.StatusBar();
			this.sbpanMsg = new System.Windows.Forms.StatusBarPanel();
			this.sbpanConnection = new System.Windows.Forms.StatusBarPanel();
			this.sbpanActiveWindow = new System.Windows.Forms.StatusBarPanel();
			this.panBrowser = new System.Windows.Forms.Panel();
			this.panSchemaBrowser = new System.Windows.Forms.Panel();
			this.tvDBObjects = new System.Windows.Forms.TreeView();
			this.toolbarSchemaBrowser = new System.Windows.Forms.ToolBar();
			this.tbbSchBrwRefresh = new System.Windows.Forms.ToolBarButton();
			this.tbbSchBrwExpandAll = new System.Windows.Forms.ToolBarButton();
			this.tbbSchBrwCollapseAll = new System.Windows.Forms.ToolBarButton();
			this.panSchemaSelect = new System.Windows.Forms.Panel();
			this.cbSchemas = new System.Windows.Forms.ComboBox();
			this.label2 = new System.Windows.Forms.Label();
			this.splitterBrowser = new System.Windows.Forms.Splitter();
			this.panConnections = new System.Windows.Forms.Panel();
			this.tvConnections = new System.Windows.Forms.TreeView();
			this.toolbarConnections = new System.Windows.Forms.ToolBar();
			this.tbbConBrwRefresh = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwExpandAll = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwCollapseAll = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwMinRestToggle = new System.Windows.Forms.ToolBarButton();
			this.tbbConBrwClose = new System.Windows.Forms.ToolBarButton();
			this.label3 = new System.Windows.Forms.Label();
			this.panBrowserTop = new System.Windows.Forms.Panel();
			this.btnCloseBrw = new System.Windows.Forms.Button();
			this.label1 = new System.Windows.Forms.Label();
			this.cmConnections = new System.Windows.Forms.ContextMenu();
			this.cmiReconnect = new System.Windows.Forms.MenuItem();
			this.cmiDisconnect = new System.Windows.Forms.MenuItem();
			this.menuItem5 = new System.Windows.Forms.MenuItem();
			this.cmiConExpand = new System.Windows.Forms.MenuItem();
			this.cmiConCollapse = new System.Windows.Forms.MenuItem();
			this.menuItem67 = new System.Windows.Forms.MenuItem();
			this.cmiConCloseAllWins = new System.Windows.Forms.MenuItem();
			this.splitterMain = new System.Windows.Forms.Splitter();
			this.cmWindows = new System.Windows.Forms.ContextMenu();
			this.cmiShowWindow = new System.Windows.Forms.MenuItem();
			this.cmiCloseWindow = new System.Windows.Forms.MenuItem();
			this.tabWindows = new System.Windows.Forms.TabControl();
			this.cmDBObjs = new System.Windows.Forms.ContextMenu();
			this.cmiNewDBObj = new System.Windows.Forms.MenuItem();
			this.menuItem4 = new System.Windows.Forms.MenuItem();
			this.menuItem6 = new System.Windows.Forms.MenuItem();
			this.cmiDropAll = new System.Windows.Forms.MenuItem();
			this.menuItem64 = new System.Windows.Forms.MenuItem();
			this.cmiDBObjsExpand = new System.Windows.Forms.MenuItem();
			this.cmiDBObjsCollapse = new System.Windows.Forms.MenuItem();
			this.cmDBObj = new System.Windows.Forms.ContextMenu();
			this.cmiEditDBObj = new System.Windows.Forms.MenuItem();
			this.cmiDropDBObj = new System.Windows.Forms.MenuItem();
			((System.ComponentModel.ISupportInitialize)(this.sbpanMsg)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanConnection)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanActiveWindow)).BeginInit();
			this.panBrowser.SuspendLayout();
			this.panSchemaBrowser.SuspendLayout();
			this.panSchemaSelect.SuspendLayout();
			this.panConnections.SuspendLayout();
			this.panBrowserTop.SuspendLayout();
			this.SuspendLayout();
			// 
			// mmMainMenu
			// 
			this.mmMainMenu.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					   this.menuItem1,
																					   this.miSearch,
																					   this.miCreate,
																					   this.miObjects,
																					   this.miTransaction,
																					   this.menuItem3});
			// 
			// menuItem1
			// 
			this.menuItem1.Index = 0;
			this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.miNewCon,
																					  this.miConHist,
																					  this.menuItem7,
																					  this.miBrowser,
																					  this.miSQLWindow,
																					  this.menuItem28,
																					  this.miSqlnet,
																					  this.miTnsnames,
																					  this.menuItem8,
																					  this.miExit});
			this.menuItem1.Text = "&File";
			// 
			// miNewCon
			// 
			this.miNewCon.Index = 0;
			this.miNewCon.Shortcut = System.Windows.Forms.Shortcut.CtrlN;
			this.miNewCon.Text = "New &Connection";
			this.miNewCon.Click += new System.EventHandler(this.miNewCon_Click);
			// 
			// miConHist
			// 
			this.miConHist.Index = 1;
			this.miConHist.Shortcut = System.Windows.Forms.Shortcut.CtrlH;
			this.miConHist.Text = "Connection &History";
			this.miConHist.Click += new System.EventHandler(this.miConHist_Click);
			// 
			// menuItem7
			// 
			this.menuItem7.Index = 2;
			this.menuItem7.Text = "-";
			// 
			// miBrowser
			// 
			this.miBrowser.Checked = true;
			this.miBrowser.Index = 3;
			this.miBrowser.Shortcut = System.Windows.Forms.Shortcut.CtrlB;
			this.miBrowser.Text = "&Browser";
			this.miBrowser.Click += new System.EventHandler(this.miBrowser_Click);
			// 
			// miSQLWindow
			// 
			this.miSQLWindow.Index = 4;
			this.miSQLWindow.Shortcut = System.Windows.Forms.Shortcut.CtrlShiftS;
			this.miSQLWindow.Text = "SQL &Window";
			this.miSQLWindow.Click += new System.EventHandler(this.miSQLWindow_Click);
			// 
			// menuItem28
			// 
			this.menuItem28.Index = 5;
			this.menuItem28.Text = "-";
			// 
			// miSqlnet
			// 
			this.miSqlnet.Index = 6;
			this.miSqlnet.Shortcut = System.Windows.Forms.Shortcut.CtrlShiftN;
			this.miSqlnet.Text = "&Sqlnet.ora";
			this.miSqlnet.Click += new System.EventHandler(this.miSqlnet_Click);
			// 
			// miTnsnames
			// 
			this.miTnsnames.Index = 7;
			this.miTnsnames.Shortcut = System.Windows.Forms.Shortcut.CtrlShiftT;
			this.miTnsnames.Text = "&Tnsnames.ora";
			this.miTnsnames.Click += new System.EventHandler(this.miTnsnames_Click);
			// 
			// menuItem8
			// 
			this.menuItem8.Index = 8;
			this.menuItem8.Text = "-";
			// 
			// miExit
			// 
			this.miExit.Index = 9;
			this.miExit.Shortcut = System.Windows.Forms.Shortcut.CtrlShiftX;
			this.miExit.Text = "E&xit";
			this.miExit.Click += new System.EventHandler(this.miExit_Click);
			// 
			// miSearch
			// 
			this.miSearch.Index = 1;
			this.miSearch.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					 this.miFindAllObjs,
																					 this.miFindDBLink,
																					 this.miFindSequence,
																					 this.miFindTable,
																					 this.miFindUser,
																					 this.menuItem10,
																					 this.miFindFunction,
																					 this.miFindInFunctCode,
																					 this.menuItem25,
																					 this.miFindProcedure,
																					 this.miFindInProcCode,
																					 this.menuItem9,
																					 this.miFindTrigger,
																					 this.miFindInTriggerCode,
																					 this.menuItem12,
																					 this.miFindView,
																					 this.miFindInViewCode});
			this.miSearch.Text = "&Search";
			// 
			// miFindAllObjs
			// 
			this.miFindAllObjs.Index = 0;
			this.miFindAllObjs.Shortcut = System.Windows.Forms.Shortcut.CtrlF;
			this.miFindAllObjs.Text = "&All Objects";
			this.miFindAllObjs.Click += new System.EventHandler(this.miFindAllObjs_Click);
			// 
			// miFindDBLink
			// 
			this.miFindDBLink.Index = 1;
			this.miFindDBLink.Text = "&DB Link";
			this.miFindDBLink.Click += new System.EventHandler(this.miFindDBLink_Click);
			// 
			// miFindSequence
			// 
			this.miFindSequence.Index = 2;
			this.miFindSequence.Text = "&Sequence";
			this.miFindSequence.Click += new System.EventHandler(this.miFindSequence_Click);
			// 
			// miFindTable
			// 
			this.miFindTable.Index = 3;
			this.miFindTable.Text = "&Table";
			this.miFindTable.Click += new System.EventHandler(this.miFindTable_Click);
			// 
			// miFindUser
			// 
			this.miFindUser.Index = 4;
			this.miFindUser.Text = "&User";
			this.miFindUser.Click += new System.EventHandler(this.miFindUser_Click);
			// 
			// menuItem10
			// 
			this.menuItem10.Index = 5;
			this.menuItem10.Text = "-";
			// 
			// miFindFunction
			// 
			this.miFindFunction.Index = 6;
			this.miFindFunction.Text = "&Function";
			this.miFindFunction.Click += new System.EventHandler(this.miFindFunction_Click);
			// 
			// miFindInFunctCode
			// 
			this.miFindInFunctCode.Index = 7;
			this.miFindInFunctCode.Text = "In F&unction Code";
			this.miFindInFunctCode.Click += new System.EventHandler(this.miFindInFunctCode_Click);
			// 
			// menuItem25
			// 
			this.menuItem25.Index = 8;
			this.menuItem25.Text = "-";
			// 
			// miFindProcedure
			// 
			this.miFindProcedure.Index = 9;
			this.miFindProcedure.Text = "&Procedure";
			this.miFindProcedure.Click += new System.EventHandler(this.miFindProcedure_Click);
			// 
			// miFindInProcCode
			// 
			this.miFindInProcCode.Index = 10;
			this.miFindInProcCode.Text = "In P&rocedure Code";
			this.miFindInProcCode.Click += new System.EventHandler(this.miFindInProcCode_Click);
			// 
			// menuItem9
			// 
			this.menuItem9.Index = 11;
			this.menuItem9.Text = "-";
			// 
			// miFindTrigger
			// 
			this.miFindTrigger.Index = 12;
			this.miFindTrigger.Text = "Tr&igger";
			this.miFindTrigger.Click += new System.EventHandler(this.miFindTrigger_Click);
			// 
			// miFindInTriggerCode
			// 
			this.miFindInTriggerCode.Index = 13;
			this.miFindInTriggerCode.Text = "Tri&gger Code";
			this.miFindInTriggerCode.Click += new System.EventHandler(this.miFindInTriggerCode_Click);
			// 
			// menuItem12
			// 
			this.menuItem12.Index = 14;
			this.menuItem12.Text = "-";
			// 
			// miFindView
			// 
			this.miFindView.Index = 15;
			this.miFindView.Text = "&View";
			this.miFindView.Click += new System.EventHandler(this.miFindView_Click);
			// 
			// miFindInViewCode
			// 
			this.miFindInViewCode.Index = 16;
			this.miFindInViewCode.Text = "Vie&w Code";
			this.miFindInViewCode.Click += new System.EventHandler(this.miFindInViewCode_Click);
			// 
			// miCreate
			// 
			this.miCreate.Index = 2;
			this.miCreate.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					 this.miNewDBLink,
																					 this.miNewFunction,
																					 this.miNewProcedure,
																					 this.miNewSequence,
																					 this.miNewTable,
																					 this.miNewTrigger,
																					 this.miNewUser,
																					 this.miNewView});
			this.miCreate.Text = "&Create";
			// 
			// miNewDBLink
			// 
			this.miNewDBLink.Index = 0;
			this.miNewDBLink.Text = "&DB Link";
			this.miNewDBLink.Click += new System.EventHandler(this.miNewDBLink_Click);
			// 
			// miNewFunction
			// 
			this.miNewFunction.Index = 1;
			this.miNewFunction.Text = "&Function";
			this.miNewFunction.Click += new System.EventHandler(this.miNewFunction_Click);
			// 
			// miNewProcedure
			// 
			this.miNewProcedure.Index = 2;
			this.miNewProcedure.Text = "&Procedure";
			this.miNewProcedure.Click += new System.EventHandler(this.miNewProcedure_Click);
			// 
			// miNewSequence
			// 
			this.miNewSequence.Index = 3;
			this.miNewSequence.Text = "&Sequence";
			this.miNewSequence.Click += new System.EventHandler(this.miNewSequence_Click);
			// 
			// miNewTable
			// 
			this.miNewTable.Index = 4;
			this.miNewTable.Text = "&Table";
			this.miNewTable.Click += new System.EventHandler(this.miNewTable_Click);
			// 
			// miNewTrigger
			// 
			this.miNewTrigger.Index = 5;
			this.miNewTrigger.Text = "T&rigger";
			this.miNewTrigger.Click += new System.EventHandler(this.miNewTrigger_Click);
			// 
			// miNewUser
			// 
			this.miNewUser.Index = 6;
			this.miNewUser.Text = "&User";
			this.miNewUser.Click += new System.EventHandler(this.miNewUser_Click);
			// 
			// miNewView
			// 
			this.miNewView.Index = 7;
			this.miNewView.Text = "&View";
			this.miNewView.Click += new System.EventHandler(this.miNewView_Click);
			// 
			// miObjects
			// 
			this.miObjects.Index = 3;
			this.miObjects.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.miObjDBLink,
																					  this.miObjFunction,
																					  this.miObjProcedure,
																					  this.miObjSequence,
																					  this.miObjTable,
																					  this.miObjTrigger,
																					  this.miObjUser,
																					  this.miObjView});
			this.miObjects.Text = "&Objects";
			// 
			// miObjDBLink
			// 
			this.miObjDBLink.Index = 0;
			this.miObjDBLink.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						this.miEditDBLink,
																						this.miDropDBLink});
			this.miObjDBLink.Text = "&DB Link";
			// 
			// miEditDBLink
			// 
			this.miEditDBLink.Index = 0;
			this.miEditDBLink.Text = "&Edit";
			this.miEditDBLink.Click += new System.EventHandler(this.miEditDBLink_Click);
			// 
			// miDropDBLink
			// 
			this.miDropDBLink.Index = 1;
			this.miDropDBLink.Text = "&Drop";
			this.miDropDBLink.Click += new System.EventHandler(this.miDropDBLink_Click);
			// 
			// miObjFunction
			// 
			this.miObjFunction.Index = 1;
			this.miObjFunction.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						  this.miEditFunction,
																						  this.miDropFunction,
																						  this.menuItem51});
			this.miObjFunction.Text = "&Function";
			// 
			// miEditFunction
			// 
			this.miEditFunction.Index = 0;
			this.miEditFunction.Text = "&Edit";
			this.miEditFunction.Click += new System.EventHandler(this.miEditFunction_Click);
			// 
			// miDropFunction
			// 
			this.miDropFunction.Index = 1;
			this.miDropFunction.Text = "&Drop";
			this.miDropFunction.Click += new System.EventHandler(this.miDropFunction_Click);
			// 
			// menuItem51
			// 
			this.menuItem51.Index = 2;
			this.menuItem51.Text = "&Compile";
			this.menuItem51.Visible = false;
			// 
			// miObjProcedure
			// 
			this.miObjProcedure.Index = 2;
			this.miObjProcedure.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						   this.miEditProcedure,
																						   this.miDropProcedure,
																						   this.menuItem53});
			this.miObjProcedure.Text = "&Procedure";
			// 
			// miEditProcedure
			// 
			this.miEditProcedure.Index = 0;
			this.miEditProcedure.Text = "&Edit";
			this.miEditProcedure.Click += new System.EventHandler(this.miEditProcedure_Click);
			// 
			// miDropProcedure
			// 
			this.miDropProcedure.Index = 1;
			this.miDropProcedure.Text = "&Drop";
			this.miDropProcedure.Click += new System.EventHandler(this.miDropProcedure_Click);
			// 
			// menuItem53
			// 
			this.menuItem53.Index = 2;
			this.menuItem53.Text = "&Compile";
			this.menuItem53.Visible = false;
			// 
			// miObjSequence
			// 
			this.miObjSequence.Index = 3;
			this.miObjSequence.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						  this.miEditSequence,
																						  this.miDropSequence,
																						  this.menuItem47});
			this.miObjSequence.Text = "&Sequence";
			// 
			// miEditSequence
			// 
			this.miEditSequence.Index = 0;
			this.miEditSequence.Text = "&Edit";
			this.miEditSequence.Click += new System.EventHandler(this.miEditSequence_Click);
			// 
			// miDropSequence
			// 
			this.miDropSequence.Index = 1;
			this.miDropSequence.Text = "&Drop";
			this.miDropSequence.Click += new System.EventHandler(this.miDropSequence_Click);
			// 
			// menuItem47
			// 
			this.menuItem47.Index = 2;
			this.menuItem47.Text = "&View SQL";
			this.menuItem47.Visible = false;
			// 
			// miObjTable
			// 
			this.miObjTable.Index = 4;
			this.miObjTable.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					   this.menuItem39,
																					   this.menuItem40,
																					   this.miEditTable,
																					   this.menuItem42,
																					   this.menuItem43,
																					   this.miDropTable});
			this.miObjTable.Text = "&Table";
			// 
			// menuItem39
			// 
			this.menuItem39.Index = 0;
			this.menuItem39.Text = "&Describe";
			this.menuItem39.Visible = false;
			// 
			// menuItem40
			// 
			this.menuItem40.Index = 1;
			this.menuItem40.Text = "&Edit Table Definition";
			this.menuItem40.Visible = false;
			// 
			// miEditTable
			// 
			this.miEditTable.Index = 2;
			this.miEditTable.Text = "&Edit";
			this.miEditTable.Click += new System.EventHandler(this.miEditTable_Click);
			// 
			// menuItem42
			// 
			this.menuItem42.Index = 3;
			this.menuItem42.Text = "&View SQL";
			this.menuItem42.Visible = false;
			// 
			// menuItem43
			// 
			this.menuItem43.Index = 4;
			this.menuItem43.Text = "&Find Data in Table";
			this.menuItem43.Visible = false;
			// 
			// miDropTable
			// 
			this.miDropTable.Index = 5;
			this.miDropTable.Text = "&Drop";
			this.miDropTable.Click += new System.EventHandler(this.miDropTable_Click);
			// 
			// miObjTrigger
			// 
			this.miObjTrigger.Index = 5;
			this.miObjTrigger.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						 this.miEditTrigger,
																						 this.miDropTrigger,
																						 this.menuItem56});
			this.miObjTrigger.Text = "T&rigger";
			// 
			// miEditTrigger
			// 
			this.miEditTrigger.Index = 0;
			this.miEditTrigger.Text = "&Edit";
			this.miEditTrigger.Click += new System.EventHandler(this.miEditTrigger_Click);
			// 
			// miDropTrigger
			// 
			this.miDropTrigger.Index = 1;
			this.miDropTrigger.Text = "&Drop";
			this.miDropTrigger.Click += new System.EventHandler(this.miDropTrigger_Click);
			// 
			// menuItem56
			// 
			this.menuItem56.Index = 2;
			this.menuItem56.Text = "&Compile";
			this.menuItem56.Visible = false;
			// 
			// miObjUser
			// 
			this.miObjUser.Index = 6;
			this.miObjUser.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.miEditUser,
																					  this.miDropUser});
			this.miObjUser.Text = "&User";
			// 
			// miEditUser
			// 
			this.miEditUser.Index = 0;
			this.miEditUser.Text = "&Edit";
			this.miEditUser.Click += new System.EventHandler(this.miEditUser_Click);
			// 
			// miDropUser
			// 
			this.miDropUser.Index = 1;
			this.miDropUser.Text = "&Drop";
			this.miDropUser.Click += new System.EventHandler(this.miDropUser_Click);
			// 
			// miObjView
			// 
			this.miObjView.Index = 7;
			this.miObjView.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.miEditView,
																					  this.miDropView});
			this.miObjView.Text = "&View";
			// 
			// miEditView
			// 
			this.miEditView.Index = 0;
			this.miEditView.Text = "&Edit";
			this.miEditView.Click += new System.EventHandler(this.miEditView_Click);
			// 
			// miDropView
			// 
			this.miDropView.Index = 1;
			this.miDropView.Text = "&Drop";
			this.miDropView.Click += new System.EventHandler(this.miDropView_Click);
			// 
			// miTransaction
			// 
			this.miTransaction.Index = 4;
			this.miTransaction.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						  this.menuItem32,
																						  this.menuItem33});
			this.miTransaction.Text = "&Transaction";
			// 
			// menuItem32
			// 
			this.menuItem32.Index = 0;
			this.menuItem32.Text = "&Commit";
			// 
			// menuItem33
			// 
			this.menuItem33.Index = 1;
			this.menuItem33.Text = "&Rollback";
			// 
			// menuItem3
			// 
			this.menuItem3.Index = 5;
			this.menuItem3.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.miHelpContents,
																					  this.miHelpIndex,
																					  this.miHelpAbout});
			this.menuItem3.Text = "&Help";
			// 
			// miHelpContents
			// 
			this.miHelpContents.Index = 0;
			this.miHelpContents.Text = "&Contents";
			// 
			// miHelpIndex
			// 
			this.miHelpIndex.Index = 1;
			this.miHelpIndex.Text = "&Index";
			// 
			// miHelpAbout
			// 
			this.miHelpAbout.Index = 2;
			this.miHelpAbout.Text = "&About";
			this.miHelpAbout.Click += new System.EventHandler(this.miHelpAbout_Click);
			// 
			// toolbarMain
			// 
			this.toolbarMain.Appearance = System.Windows.Forms.ToolBarAppearance.Flat;
			this.toolbarMain.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																						   this.tbbNewCon,
																						   this.tbbConHist,
																						   this.tbbSeparator1,
																						   this.tbbSqlWin,
																						   this.tbbSeparator2,
																						   this.tbbBrowser,
																						   this.tbbSeparator3,
																						   this.tbbCommit,
																						   this.tbbRollback,
																						   this.tbbSeparator4,
																						   this.tbbFindDBObj,
																						   this.tbbSeparator5,
																						   this.tbbHelp,
																						   this.tbbSeparator6});
			this.toolbarMain.ButtonSize = new System.Drawing.Size(25, 25);
			this.toolbarMain.DropDownArrows = true;
			this.toolbarMain.ImageList = this.imgListMain;
			this.toolbarMain.Location = new System.Drawing.Point(0, 0);
			this.toolbarMain.Name = "toolbarMain";
			this.toolbarMain.ShowToolTips = true;
			this.toolbarMain.Size = new System.Drawing.Size(920, 28);
			this.toolbarMain.TabIndex = 1;
			this.toolbarMain.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.mainToolbar_ButtonClick);
			// 
			// tbbNewCon
			// 
			this.tbbNewCon.ImageIndex = 0;
			this.tbbNewCon.ToolTipText = "New Connection";
			// 
			// tbbConHist
			// 
			this.tbbConHist.ImageIndex = 1;
			this.tbbConHist.ToolTipText = "Connection History";
			// 
			// tbbSeparator1
			// 
			this.tbbSeparator1.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbSqlWin
			// 
			this.tbbSqlWin.ImageIndex = 2;
			this.tbbSqlWin.ToolTipText = "SQL Window";
			// 
			// tbbSeparator2
			// 
			this.tbbSeparator2.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbBrowser
			// 
			this.tbbBrowser.ImageIndex = 3;
			this.tbbBrowser.ToolTipText = "Browser";
			// 
			// tbbSeparator3
			// 
			this.tbbSeparator3.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbCommit
			// 
			this.tbbCommit.ImageIndex = 4;
			this.tbbCommit.ToolTipText = "Commit";
			// 
			// tbbRollback
			// 
			this.tbbRollback.ImageIndex = 5;
			this.tbbRollback.ToolTipText = "Rollback";
			// 
			// tbbSeparator4
			// 
			this.tbbSeparator4.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbFindDBObj
			// 
			this.tbbFindDBObj.ImageIndex = 6;
			// 
			// tbbSeparator5
			// 
			this.tbbSeparator5.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// tbbHelp
			// 
			this.tbbHelp.ImageIndex = 15;
			this.tbbHelp.ToolTipText = "Help";
			// 
			// tbbSeparator6
			// 
			this.tbbSeparator6.Style = System.Windows.Forms.ToolBarButtonStyle.Separator;
			// 
			// imgListMain
			// 
			this.imgListMain.ImageSize = new System.Drawing.Size(16, 16);
			this.imgListMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imgListMain.ImageStream")));
			this.imgListMain.TransparentColor = System.Drawing.Color.Transparent;
			// 
			// sbMain
			// 
			this.sbMain.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.sbMain.Location = new System.Drawing.Point(0, 465);
			this.sbMain.Name = "sbMain";
			this.sbMain.Panels.AddRange(new System.Windows.Forms.StatusBarPanel[] {
																					  this.sbpanMsg,
																					  this.sbpanConnection,
																					  this.sbpanActiveWindow});
			this.sbMain.ShowPanels = true;
			this.sbMain.Size = new System.Drawing.Size(920, 16);
			this.sbMain.SizingGrip = false;
			this.sbMain.TabIndex = 2;
			this.sbMain.Text = "statusBar1";
			// 
			// sbpanMsg
			// 
			this.sbpanMsg.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Spring;
			this.sbpanMsg.Text = "Ready";
			this.sbpanMsg.Width = 733;
			// 
			// sbpanConnection
			// 
			this.sbpanConnection.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Contents;
			this.sbpanConnection.Text = "ActiveConnection";
			this.sbpanConnection.Width = 102;
			// 
			// sbpanActiveWindow
			// 
			this.sbpanActiveWindow.AutoSize = System.Windows.Forms.StatusBarPanelAutoSize.Contents;
			this.sbpanActiveWindow.Text = "ActiveWindow";
			this.sbpanActiveWindow.Width = 85;
			// 
			// panBrowser
			// 
			this.panBrowser.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
			this.panBrowser.Controls.Add(this.panSchemaBrowser);
			this.panBrowser.Controls.Add(this.splitterBrowser);
			this.panBrowser.Controls.Add(this.panConnections);
			this.panBrowser.Dock = System.Windows.Forms.DockStyle.Left;
			this.panBrowser.Location = new System.Drawing.Point(0, 28);
			this.panBrowser.Name = "panBrowser";
			this.panBrowser.Size = new System.Drawing.Size(264, 437);
			this.panBrowser.TabIndex = 3;
			// 
			// panSchemaBrowser
			// 
			this.panSchemaBrowser.BackColor = System.Drawing.SystemColors.Control;
			this.panSchemaBrowser.Controls.Add(this.tvDBObjects);
			this.panSchemaBrowser.Controls.Add(this.toolbarSchemaBrowser);
			this.panSchemaBrowser.Controls.Add(this.panSchemaSelect);
			this.panSchemaBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
			this.panSchemaBrowser.Location = new System.Drawing.Point(0, 173);
			this.panSchemaBrowser.Name = "panSchemaBrowser";
			this.panSchemaBrowser.Size = new System.Drawing.Size(260, 260);
			this.panSchemaBrowser.TabIndex = 2;
			// 
			// tvDBObjects
			// 
			this.tvDBObjects.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tvDBObjects.ImageList = this.imgListMain;
			this.tvDBObjects.Location = new System.Drawing.Point(0, 51);
			this.tvDBObjects.Name = "tvDBObjects";
			this.tvDBObjects.Size = new System.Drawing.Size(260, 209);
			this.tvDBObjects.TabIndex = 10;
			this.tvDBObjects.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvDBObjects_AfterSelect);
			// 
			// toolbarSchemaBrowser
			// 
			this.toolbarSchemaBrowser.Appearance = System.Windows.Forms.ToolBarAppearance.Flat;
			this.toolbarSchemaBrowser.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.toolbarSchemaBrowser.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.toolbarSchemaBrowser.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																									this.tbbSchBrwRefresh,
																									this.tbbSchBrwExpandAll,
																									this.tbbSchBrwCollapseAll});
			this.toolbarSchemaBrowser.ButtonSize = new System.Drawing.Size(23, 22);
			this.toolbarSchemaBrowser.DropDownArrows = true;
			this.toolbarSchemaBrowser.ImageList = this.imgListMain;
			this.toolbarSchemaBrowser.Location = new System.Drawing.Point(0, 22);
			this.toolbarSchemaBrowser.Name = "toolbarSchemaBrowser";
			this.toolbarSchemaBrowser.ShowToolTips = true;
			this.toolbarSchemaBrowser.Size = new System.Drawing.Size(260, 29);
			this.toolbarSchemaBrowser.TabIndex = 9;
			this.toolbarSchemaBrowser.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.toolbarSchemaBrowser_ButtonClick);
			// 
			// tbbSchBrwRefresh
			// 
			this.tbbSchBrwRefresh.ImageIndex = 27;
			// 
			// tbbSchBrwExpandAll
			// 
			this.tbbSchBrwExpandAll.ImageIndex = 25;
			this.tbbSchBrwExpandAll.Tag = "SchBrwExpandAll";
			// 
			// tbbSchBrwCollapseAll
			// 
			this.tbbSchBrwCollapseAll.ImageIndex = 26;
			this.tbbSchBrwCollapseAll.Tag = "SchBrwCollapseAll";
			// 
			// panSchemaSelect
			// 
			this.panSchemaSelect.BackColor = System.Drawing.SystemColors.Control;
			this.panSchemaSelect.Controls.Add(this.cbSchemas);
			this.panSchemaSelect.Controls.Add(this.label2);
			this.panSchemaSelect.Dock = System.Windows.Forms.DockStyle.Top;
			this.panSchemaSelect.Location = new System.Drawing.Point(0, 0);
			this.panSchemaSelect.Name = "panSchemaSelect";
			this.panSchemaSelect.Size = new System.Drawing.Size(260, 22);
			this.panSchemaSelect.TabIndex = 8;
			// 
			// cbSchemas
			// 
			this.cbSchemas.Dock = System.Windows.Forms.DockStyle.Fill;
			this.cbSchemas.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.cbSchemas.Location = new System.Drawing.Point(72, 0);
			this.cbSchemas.Name = "cbSchemas";
			this.cbSchemas.Size = new System.Drawing.Size(188, 21);
			this.cbSchemas.TabIndex = 7;
			this.cbSchemas.SelectedIndexChanged += new System.EventHandler(this.cbSchemas_SelectedIndexChanged);
			// 
			// label2
			// 
			this.label2.BackColor = System.Drawing.SystemColors.Control;
			this.label2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.label2.Dock = System.Windows.Forms.DockStyle.Left;
			this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label2.Location = new System.Drawing.Point(0, 0);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(72, 22);
			this.label2.TabIndex = 6;
			this.label2.Text = "Schemas:";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
			// 
			// splitterBrowser
			// 
			this.splitterBrowser.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.splitterBrowser.Dock = System.Windows.Forms.DockStyle.Top;
			this.splitterBrowser.Location = new System.Drawing.Point(0, 168);
			this.splitterBrowser.Name = "splitterBrowser";
			this.splitterBrowser.Size = new System.Drawing.Size(260, 5);
			this.splitterBrowser.TabIndex = 1;
			this.splitterBrowser.TabStop = false;
			// 
			// panConnections
			// 
			this.panConnections.BackColor = System.Drawing.SystemColors.Control;
			this.panConnections.Controls.Add(this.tvConnections);
			this.panConnections.Controls.Add(this.toolbarConnections);
			this.panConnections.Controls.Add(this.label3);
			this.panConnections.Controls.Add(this.panBrowserTop);
			this.panConnections.Dock = System.Windows.Forms.DockStyle.Top;
			this.panConnections.Location = new System.Drawing.Point(0, 0);
			this.panConnections.Name = "panConnections";
			this.panConnections.Size = new System.Drawing.Size(260, 168);
			this.panConnections.TabIndex = 0;
			// 
			// tvConnections
			// 
			this.tvConnections.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tvConnections.ImageList = this.imgListMain;
			this.tvConnections.Location = new System.Drawing.Point(0, 61);
			this.tvConnections.Name = "tvConnections";
			this.tvConnections.Size = new System.Drawing.Size(260, 107);
			this.tvConnections.TabIndex = 11;
			this.tvConnections.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvConnections_AfterSelect);
			// 
			// toolbarConnections
			// 
			this.toolbarConnections.Appearance = System.Windows.Forms.ToolBarAppearance.Flat;
			this.toolbarConnections.BackColor = System.Drawing.Color.FromArgb(((System.Byte)(255)), ((System.Byte)(255)), ((System.Byte)(192)));
			this.toolbarConnections.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.toolbarConnections.Buttons.AddRange(new System.Windows.Forms.ToolBarButton[] {
																								  this.tbbConBrwRefresh,
																								  this.tbbConBrwExpandAll,
																								  this.tbbConBrwCollapseAll,
																								  this.tbbConBrwMinRestToggle,
																								  this.tbbConBrwClose});
			this.toolbarConnections.ButtonSize = new System.Drawing.Size(23, 22);
			this.toolbarConnections.DropDownArrows = true;
			this.toolbarConnections.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.toolbarConnections.ImageList = this.imgListMain;
			this.toolbarConnections.Location = new System.Drawing.Point(0, 32);
			this.toolbarConnections.Name = "toolbarConnections";
			this.toolbarConnections.ShowToolTips = true;
			this.toolbarConnections.Size = new System.Drawing.Size(260, 29);
			this.toolbarConnections.TabIndex = 10;
			this.toolbarConnections.ButtonClick += new System.Windows.Forms.ToolBarButtonClickEventHandler(this.toolbarConnections_ButtonClick);
			// 
			// tbbConBrwRefresh
			// 
			this.tbbConBrwRefresh.ImageIndex = 27;
			this.tbbConBrwRefresh.ToolTipText = "Refresh Connections and Windows";
			// 
			// tbbConBrwExpandAll
			// 
			this.tbbConBrwExpandAll.ImageIndex = 25;
			this.tbbConBrwExpandAll.Tag = "ConBrwExpandAll";
			this.tbbConBrwExpandAll.ToolTipText = "Expand All nodes under Connections";
			// 
			// tbbConBrwCollapseAll
			// 
			this.tbbConBrwCollapseAll.ImageIndex = 26;
			this.tbbConBrwCollapseAll.Tag = "ConBrwCollapseAll";
			this.tbbConBrwCollapseAll.ToolTipText = "Collapse All nodes under Connections";
			// 
			// tbbConBrwMinRestToggle
			// 
			this.tbbConBrwMinRestToggle.ImageIndex = 22;
			this.tbbConBrwMinRestToggle.Tag = "ConBrwMinRestToggle";
			this.tbbConBrwMinRestToggle.ToolTipText = "Hide Connections and Windows";
			// 
			// tbbConBrwClose
			// 
			this.tbbConBrwClose.ImageIndex = 24;
			this.tbbConBrwClose.Tag = "ConBrwClose";
			this.tbbConBrwClose.ToolTipText = "Close Browser";
			this.tbbConBrwClose.Visible = false;
			// 
			// label3
			// 
			this.label3.BackColor = System.Drawing.SystemColors.Control;
			this.label3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.label3.Dock = System.Windows.Forms.DockStyle.Top;
			this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label3.Location = new System.Drawing.Point(0, 16);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(260, 16);
			this.label3.TabIndex = 8;
			this.label3.Text = "Connections and Windows";
			// 
			// panBrowserTop
			// 
			this.panBrowserTop.BackColor = System.Drawing.Color.Brown;
			this.panBrowserTop.Controls.Add(this.btnCloseBrw);
			this.panBrowserTop.Controls.Add(this.label1);
			this.panBrowserTop.Dock = System.Windows.Forms.DockStyle.Top;
			this.panBrowserTop.Location = new System.Drawing.Point(0, 0);
			this.panBrowserTop.Name = "panBrowserTop";
			this.panBrowserTop.Size = new System.Drawing.Size(260, 16);
			this.panBrowserTop.TabIndex = 6;
			// 
			// btnCloseBrw
			// 
			this.btnCloseBrw.BackColor = System.Drawing.SystemColors.Control;
			this.btnCloseBrw.Dock = System.Windows.Forms.DockStyle.Right;
			this.btnCloseBrw.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
			this.btnCloseBrw.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.btnCloseBrw.ImageIndex = 24;
			this.btnCloseBrw.ImageList = this.imgListMain;
			this.btnCloseBrw.Location = new System.Drawing.Point(244, 0);
			this.btnCloseBrw.Name = "btnCloseBrw";
			this.btnCloseBrw.Size = new System.Drawing.Size(16, 16);
			this.btnCloseBrw.TabIndex = 6;
			this.btnCloseBrw.Click += new System.EventHandler(this.btnCloseBrw_Click);
			// 
			// label1
			// 
			this.label1.BackColor = System.Drawing.SystemColors.Control;
			this.label1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
			this.label1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label1.Location = new System.Drawing.Point(0, 0);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(260, 16);
			this.label1.TabIndex = 5;
			this.label1.Text = "Browser";
			// 
			// cmConnections
			// 
			this.cmConnections.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																						  this.cmiReconnect,
																						  this.cmiDisconnect,
																						  this.menuItem5,
																						  this.cmiConExpand,
																						  this.cmiConCollapse,
																						  this.menuItem67,
																						  this.cmiConCloseAllWins});
			// 
			// cmiReconnect
			// 
			this.cmiReconnect.Index = 0;
			this.cmiReconnect.Text = "&Reconnect";
			// 
			// cmiDisconnect
			// 
			this.cmiDisconnect.Index = 1;
			this.cmiDisconnect.Text = "&Disconnect";
			this.cmiDisconnect.Click += new System.EventHandler(this.cmiDisconnect_Click);
			// 
			// menuItem5
			// 
			this.menuItem5.Index = 2;
			this.menuItem5.Text = "-";
			// 
			// cmiConExpand
			// 
			this.cmiConExpand.Index = 3;
			this.cmiConExpand.Text = "&Expand";
			this.cmiConExpand.Click += new System.EventHandler(this.cmiConExpand_Click);
			// 
			// cmiConCollapse
			// 
			this.cmiConCollapse.Index = 4;
			this.cmiConCollapse.Text = "C&ollapse";
			this.cmiConCollapse.Click += new System.EventHandler(this.cmiConCollapse_Click);
			// 
			// menuItem67
			// 
			this.menuItem67.Index = 5;
			this.menuItem67.Text = "-";
			// 
			// cmiConCloseAllWins
			// 
			this.cmiConCloseAllWins.Index = 6;
			this.cmiConCloseAllWins.Text = "&Close All Windows";
			this.cmiConCloseAllWins.Click += new System.EventHandler(this.cmiConCloseAllWins_Click);
			// 
			// splitterMain
			// 
			this.splitterMain.BackColor = System.Drawing.SystemColors.ControlDarkDark;
			this.splitterMain.Location = new System.Drawing.Point(264, 28);
			this.splitterMain.Name = "splitterMain";
			this.splitterMain.Size = new System.Drawing.Size(5, 437);
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
			this.cmiShowWindow.Text = "&Show Window";
			this.cmiShowWindow.Click += new System.EventHandler(this.cmiShowWindow_Click);
			// 
			// cmiCloseWindow
			// 
			this.cmiCloseWindow.Index = 1;
			this.cmiCloseWindow.Text = "&Close Window";
			this.cmiCloseWindow.Click += new System.EventHandler(this.cmiCloseWindow_Click);
			// 
			// tabWindows
			// 
			this.tabWindows.Dock = System.Windows.Forms.DockStyle.Fill;
			this.tabWindows.ImageList = this.imgListMain;
			this.tabWindows.Location = new System.Drawing.Point(269, 28);
			this.tabWindows.Name = "tabWindows";
			this.tabWindows.SelectedIndex = 0;
			this.tabWindows.Size = new System.Drawing.Size(651, 437);
			this.tabWindows.TabIndex = 6;
			this.tabWindows.SelectedIndexChanged += new System.EventHandler(this.tabWindows_SelectedIndexChanged);
			// 
			// cmDBObjs
			// 
			this.cmDBObjs.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					 this.cmiNewDBObj,
																					 this.menuItem4,
																					 this.menuItem6,
																					 this.cmiDropAll,
																					 this.menuItem64,
																					 this.cmiDBObjsExpand,
																					 this.cmiDBObjsCollapse});
			// 
			// cmiNewDBObj
			// 
			this.cmiNewDBObj.Index = 0;
			this.cmiNewDBObj.Text = "&New";
			this.cmiNewDBObj.Click += new System.EventHandler(this.cmiNewDBObj_Click);
			// 
			// menuItem4
			// 
			this.menuItem4.Index = 1;
			this.menuItem4.Text = "&Refresh";
			// 
			// menuItem6
			// 
			this.menuItem6.Index = 2;
			this.menuItem6.Text = "-";
			// 
			// cmiDropAll
			// 
			this.cmiDropAll.Index = 3;
			this.cmiDropAll.Text = "&Drop All";
			this.cmiDropAll.Click += new System.EventHandler(this.cmiDropAll_Click);
			// 
			// menuItem64
			// 
			this.menuItem64.Index = 4;
			this.menuItem64.Text = "-";
			// 
			// cmiDBObjsExpand
			// 
			this.cmiDBObjsExpand.Index = 5;
			this.cmiDBObjsExpand.Text = "&Expand";
			this.cmiDBObjsExpand.Click += new System.EventHandler(this.cmiDBObjsExpand_Click);
			// 
			// cmiDBObjsCollapse
			// 
			this.cmiDBObjsCollapse.Index = 6;
			this.cmiDBObjsCollapse.Text = "C&ollapse";
			this.cmiDBObjsCollapse.Click += new System.EventHandler(this.cmiDBObjsCollapse_Click);
			// 
			// cmDBObj
			// 
			this.cmDBObj.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					this.cmiEditDBObj,
																					this.cmiDropDBObj});
			// 
			// cmiEditDBObj
			// 
			this.cmiEditDBObj.Index = 0;
			this.cmiEditDBObj.Text = "Edit";
			this.cmiEditDBObj.Click += new System.EventHandler(this.cmiEditDBObj_Click);
			// 
			// cmiDropDBObj
			// 
			this.cmiDropDBObj.Index = 1;
			this.cmiDropDBObj.Text = "Drop";
			this.cmiDropDBObj.Click += new System.EventHandler(this.cmiDropDBObj_Click);
			// 
			// DBManagerMainForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(920, 481);
			this.Controls.Add(this.tabWindows);
			this.Controls.Add(this.splitterMain);
			this.Controls.Add(this.panBrowser);
			this.Controls.Add(this.sbMain);
			this.Controls.Add(this.toolbarMain);
			this.Menu = this.mmMainMenu;
			this.Name = "DBManagerMainForm";
			this.Text = "Database Manager";
			this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
			this.Load += new System.EventHandler(this.DBManagerMainForm_Load);
			((System.ComponentModel.ISupportInitialize)(this.sbpanMsg)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanConnection)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.sbpanActiveWindow)).EndInit();
			this.panBrowser.ResumeLayout(false);
			this.panSchemaBrowser.ResumeLayout(false);
			this.panSchemaSelect.ResumeLayout(false);
			this.panConnections.ResumeLayout(false);
			this.panBrowserTop.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/***************************************************************************/
		public DBManagerMainForm()
		{
			// Required for Windows Form Designer support
			InitializeComponent();
			frmSqlnet = new SqlnetForm();
			frmTnsora = new TnsoraForm();
			
			conHistForm = new ConnectionHistoryForm(frmSqlnet, frmTnsora, this);
			validDBs = new ValidDatabasesInfo();
		}
/***************************************************************************/
		public TreeNode getWindowNodeByName(string winName)
		{
			for (int i=0; i < this.tvConnections.Nodes.Count; i++)
			{
				for (int j=0; j < this.tvConnections.Nodes[i].Nodes.Count; j++)
				{
					if (this.tvConnections.Nodes[i].Nodes[j].Text.ToUpper().Equals(winName.Trim().ToUpper()))
						return this.tvConnections.Nodes[i].Nodes[j];
				}
			}
			return null;
		}
/***************************************************************************/
		// Clean up any resources being used.
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
		private void DBManagerMainForm_Load(object sender, System.EventArgs e)
		{
			this.tvConnections.MouseDown += new MouseEventHandler(this.tvConnections_MouseDown);
			this.tvDBObjects.MouseDown += new MouseEventHandler(this.tvDBObjects_MouseDown);
			
			this.tabWindows.Dock = DockStyle.Fill;
			this.tabWindows.Hide();

			//this.panBrowser.Hide();
			init_status_bar();
			init_connectionsToolbar();
			init_schemaBrowserToolbar();
			init_conWinsTree();
			init_windows_tabs();
			init_schemaBrowser(null, "", "");
			setMainMenuBeforeLogin();
			
			deActivateObjectsMenu();	//de-activate all menu items related to objects

			if (this.conHistForm.conHist.connHistTable.Rows.Count == 0)
			{
				if (MessageBox.Show("Connection history is not available. Do you want to make a New Connection?", 
					"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
				{
					frmNewConn frm = new frmNewConn(this.conHistForm);
					frm.ShowDialog();
				}
			}
			else
			{
				conHistForm.ShowDialog();
			}
		}
/***************************************************************************/
		private void setMainMenuBeforeLogin()
		{
			this.miSearch.Visible = false;
			this.miCreate.Visible = false;
			this.miObjects.Visible = false;
			this.miTransaction.Visible = false;
		}
/***************************************************************************/
		private void setMainMenuAfterLogin()
		{
			this.miSearch.Visible = true;
			this.miCreate.Visible = true;
			this.miObjects.Visible = true;
			this.miTransaction.Visible = true;
		}
/***************************************************************************/
		private void init_status_bar()
		{
			this.sbMain.Panels[0].Text = "Ready";
			this.sbMain.Panels[1].Text = "";
			this.sbMain.Panels[2].Text = "";
		}
/***************************************************************************/
		private void init_conWinsTree()
		{
			this.tvConnections.AfterSelect += new TreeViewEventHandler(tvConnections_AfterSelect);
			this.tvConnections.Select();
			this.panConnections.Size = new Size(0, this.panBrowser.Height/3);
		}
/***************************************************************************/
		private void init_windows_tabs()
		{
		}
/***************************************************************************/
		private void tvConnections_AfterSelect(object sender, TreeViewEventArgs e)
		{
			TreeView tvCon = (TreeView)sender;
			TreeNode node = e.Node;
			if (node.Text.IndexOf("@") >= 0)
			{
				if (node.Nodes.Count > 0)
				{
					//select the first window
					tvCon.SelectedNode = node.Nodes[0];
				}
				else
				{
					this.sbMain.Panels[1].Text = node.Text;	//Show Active Connection
					if (node.ImageIndex == MyImageList.getImgList().getImageIndex("CONNECTED"))
					{
						//this.sbpanConnection.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\connected.ico");
						this.sbpanConnection.Icon = MyImageList.getImgList().getIcon("CONNECTED");
					}
					else if (node.ImageIndex == MyImageList.getImgList().getImageIndex("DISCONNECTED"))
					{
						//this.sbpanConnection.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\disconnected.ico");
						this.sbpanConnection.Icon = MyImageList.getImgList().getIcon("DISCONNECTED");
					}
					this.sbpanActiveWindow.Text = "";
					this.sbpanActiveWindow.Icon = null;
					this.sbMain.Invalidate();
				}
			}
			else	//user has selected a window
			{
				this.sbMain.Panels[1].Text = node.Parent.Text;	//Show Active Connection
				this.sbMain.Panels[2].Text = node.Text;			//Show Active Window
				if (node.Parent.ImageIndex == MyImageList.getImgList().getImageIndex("CONNECTED"))
				{
					//this.sbpanConnection.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\connected.ico");
					this.sbpanConnection.Icon = MyImageList.getImgList().getIcon("CONNECTED");
				}
				else if (node.Parent.ImageIndex == MyImageList.getImgList().getImageIndex("DISCONNECTED"))
				{
					//this.sbpanConnection.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\disconnected.ico");
					this.sbpanConnection.Icon = MyImageList.getImgList().getIcon("DISCONNECTED");
				}
				//set the window sub panel icon
				setWindowPanelIcon(tvCon.SelectedNode.ImageIndex);
				
				//select the appropriate tab page now
				string name = node.Parent.Text + " - " + node.Text;
				TabPage tpg = getTabPage(name);
				if (tpg != null)
				{
					this.tabWindows.SelectedTab = tpg;
				}
			}
		}
/***************************************************************************/
		public void setWindowPanelIcon(int imgIndex)
		{
			switch (MyImageList.getImgList().getImageName(imgIndex))
			{
				case "DBLINK" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("DBLINK");
					break;
				case "COMPILED_FUNCTION" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("COMPILED_FUNCTION");
					break;
				case "FUNCTION_WITH_ERRORS" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("FUNCTION_WITH_ERRORS");
					break;
				case "COMPILED_PROCEDURE" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("COMPILED_PROCEDURE");
					break;
				case "PROCEDURE_WITH_ERRORS" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("PROCEDURE_WITH_ERRORS");
					break;
				case "SEQUENCE" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("SEQUENCE");
					break;
				case "TABLE" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("TABLE");
					break;
				case "TRIGGER_PROCEDURE" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("TRIGGER_PROCEDURE");
					break;
				case "TRIGGER_WITH_ERRORS" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("TRIGGER_WITH_ERRORS");
					break;
				case "USER" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("USER");
					break;
				case "VIEW" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("VIEW");
					break;
				case "SQL_WINDOW" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("SQL_WINDOW");
					//this.sbpanActiveWindow.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\USER").ico");
					break;
				case "FIND" : 
					this.sbpanActiveWindow.Icon = MyImageList.getImgList().getIcon("FIND");
					//this.sbpanActiveWindow.Icon = new Icon("E:\\USER")Data\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\DBManager\\icons\\USER").ico");
					break;
			}
			this.sbMain.Invalidate();
		}
/***************************************************************************/
		private TabPage getTabPage(string name)
		{
			for (int i=0; i < this.tabWindows.TabPages.Count; i++)
			{
				if (this.tabWindows.TabPages[i].Text.Equals(name))
					return this.tabWindows.TabPages[i];
			}
			return null;
		}
/***************************************************************************/
		private bool isSchemaChanged = false;
/***************************************************************************/
		public void init_schemaBrowser(ConnectionTreeNode conTN, string db_name, string schema_name)
		{
			if (conTN == null)
			{
				this.cbSchemas.Enabled = false;
				this.tvDBObjects.Enabled = false;
				this.tvDBObjects.Nodes.Clear();
				TreeNode root = new TreeNode("Database Objects", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER"), 
					new TreeNode[] 
						{
							new TreeNode("DB Links(0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Functions (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Procedures (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Sequences (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Tables (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Triggers (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Users (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER")),
							new TreeNode("Views (0)", MyImageList.getImgList().getImageIndex("FOLDER"), MyImageList.getImgList().getImageIndex("FOLDER"))
						});
				this.tvDBObjects.Nodes.Add(root);
				this.tvDBObjects.ExpandAll();
//				for (int i=0; i < this.tvDBObjects.Nodes[0].Nodes.Count; i++)
//				{
//					this.tvDBObjects.Nodes[0].Nodes[i].Collapse();
//				}
				isSchemaChanged = true;
			}
			else 
			{
				isSchemaChanged = false;
				if (conTN.database.schemas.Count == 0)
				{
					MessageBox.Show("No Schema exists in the database. Please add a new schema.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					SchemaNamePrompt frm = new SchemaNamePrompt(this, false);
					frm.ShowDialog();
					this.cbSchemas.Enabled = true;
					this.tvDBObjects.Enabled = true;
					this.cbSchemas.SelectedItem = this.cbSchemas.Items[this.cbSchemas.Items.Count - 2];
					isSchemaChanged = true;
					return;
				}
				else
				{	
					this.tvDBObjects.Nodes.Clear();
					//populate the combo box of schemas with schemas from current connection node
					IDictionaryEnumerator ie = conTN.database.schemas.GetEnumerator();
					while (ie.MoveNext())
					{
						this.cbSchemas.Items.Add(ie.Key.ToString());
					}
					this.cbSchemas.Items.Add("--Add New Schema--");
				}
				TreeNode root = conTN.database.getTreeViewOfSchema(schema_name);
				this.tvDBObjects.Nodes.Clear();
				this.tvDBObjects.Nodes.Add(root);
				this.tvDBObjects.Nodes[0].Expand();		//expand the Database Objects node
				//and select the first schema
				isSchemaChanged = true;
				this.cbSchemas.Enabled = true;
				this.tvDBObjects.Enabled = true;
				this.cbSchemas.SelectedIndex = 0;
			}
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
		[STAThreadAttribute]
		static void Main() 
		{
			Application.Run(new DBManagerMainForm());
		}
/***************************************************************************/
		private void miNewCon_Click(object sender, System.EventArgs e)
		{
			frmNewConn frm = new frmNewConn(this.conHistForm);
			frm.ShowDialog();
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
				case "ConBrwMinRestToggle" : 
				case "ConBrwMaxRestToggle" : 
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
			ToolBar tb = (ToolBar)sender;
			ToolBarButton tbb = (ToolBarButton)e.Button;
			string strBtn = (string)tbb.Tag;
			//MessageBox.Show(strBtn);
			switch (strBtn)
			{
				case "SchBrwExpandAll" : this.tvDBObjects.ExpandAll(); break;
				case "SchBrwCollapseAll" :
					if (this.tvDBObjects.Nodes.Count > 0)
						this.tvDBObjects.Nodes[0].Expand();
					for (int i=0; i < this.tvDBObjects.Nodes[0].Nodes.Count; i++)
					{
						this.tvDBObjects.Nodes[0].Nodes[i].Collapse();
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
			//TreeNode t = tvCon.SelectedNode;
			if (tn != null && mea.Button == MouseButtons.Right)
			{
				tnRightClicked = tn;
				if (tn.Text.IndexOf("@") >= 0)
				{
					if (tn.ImageIndex == MyImageList.getImgList().getImageIndex("CONNECTED"))
					{
						this.cmConnections.MenuItems[0].Enabled = false;//Reconnect
						this.cmConnections.MenuItems[1].Enabled = true;//Disconnect
					}
					else if (tn.ImageIndex == MyImageList.getImgList().getImageIndex("DISCONNECTED"))
					{
						this.cmConnections.MenuItems[0].Enabled = true;//Reconnect
						this.cmConnections.MenuItems[1].Enabled = false;//Disconnect
					}
					if (tn.Nodes.Count > 0)
					{
						if (tn.IsExpanded)
						{
							cmConnections.MenuItems[3].Enabled = false;	//disable Expand
							cmConnections.MenuItems[4].Enabled = true; //enable Collapse
						}
						else 
						{
							cmConnections.MenuItems[3].Enabled = true;	//enable Expand
							cmConnections.MenuItems[4].Enabled = false; //disable Collapse
						}
						cmConnections.MenuItems[6].Enabled = true; //enable Close All Windows
					}
					else
					{
						cmConnections.MenuItems[3].Enabled = false;	//disable Expand
						cmConnections.MenuItems[4].Enabled = false; //disable Collapse
						cmConnections.MenuItems[6].Enabled = false; //disable Close All Windows
					}
					this.tvConnections.ContextMenu = this.cmConnections;
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
		private void tvDBObjects_MouseDown(object sender, MouseEventArgs mea)
		{
			TreeView tvCon = (TreeView)sender;
			TreeNode tn = tvCon.GetNodeAt(mea.X, mea.Y);
			this.tvDBObjects.SelectedNode = tn;
			if (tn != null && mea.Button == MouseButtons.Right)
			{
				tnRightClicked = tn;
				if (tn.Text.IndexOf("(") >= 0)
				{
					this.tvDBObjects.ContextMenu = this.cmDBObjs;
					if (tn.Nodes.Count == 0)
					{
						this.cmDBObjs.MenuItems[5].Enabled = false;	//disable Expand
						this.cmDBObjs.MenuItems[6].Enabled = false; //disable Collapse
					}
					else if (tn.IsExpanded)
					{
						this.cmDBObjs.MenuItems[5].Enabled = false;	//disable Expand
						this.cmDBObjs.MenuItems[6].Enabled = true; //enable Collapse
					}
					else 
					{
						this.cmDBObjs.MenuItems[5].Enabled = true;	//enable Expand
						this.cmDBObjs.MenuItems[6].Enabled = false; //disable Collapse
					}
				}
				else if (tn.Text.Equals("Database Objects"))
				{
					this.tvDBObjects.ContextMenu = null;
				}
				else
				{
					this.tvDBObjects.ContextMenu = this.cmDBObj;
				}
				this.sbpanMsg.Text =  tn.Text;
				//this.sbMain.Invalidate();
			}
			else
			{
				this.tvDBObjects.ContextMenu = null;
			}
		}
/***************************************************************************/
		private void miExit_Click(object sender, System.EventArgs e)
		{
			if (MessageBox.Show("Are you sure, you want to exit?","DB Manager", 
									MessageBoxButtons.YesNo, MessageBoxIcon.Question)
						== DialogResult.Yes)
			{
				Application.Exit();
			}
		}
/***************************************************************************/
		private void mainToolbar_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			//Help.ShowHelp(this,"mspaint.chm");
			ToolBarButton tbbClicked = (ToolBarButton)e.Button;
			if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("CONNECT"))
			{
				this.miNewCon.PerformClick();
			}
			else if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("CONNECTION_HISTORY"))
			{
				this.miConHist.PerformClick();
			}
			else if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("BROWSER"))
			{
				this.miBrowser.PerformClick();
			}
			else if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("SQL_WINDOW"))
			{
				this.miSQLWindow.PerformClick();
			}
			else if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("FIND"))
			{
				this.miFindAllObjs.PerformClick();
			}
		}
/***************************************************************************/
		private void btnCloseBrw_Click(object sender, System.EventArgs e)
		{
			this.panBrowser.Hide();
			this.splitterMain.Hide();
			this.miBrowser.Checked = false;
		}
/***************************************************************************/
		private void toolbarConnections_ButtonClick(object sender, System.Windows.Forms.ToolBarButtonClickEventArgs e)
		{
			ToolBar tb = (ToolBar)sender;
			ToolBarButton tbbClicked = (ToolBarButton)e.Button;
			string strBtn = (string)tbbClicked.Tag;
			switch (strBtn)
			{
				case "ConBrwMinRestToggle" : 
					if (tbbClicked.ImageIndex == MyImageList.getImgList().getImageIndex("UP"))
					{
						tbbClicked.ImageIndex = MyImageList.getImgList().getImageIndex("DOWN");
						this.panConnections.Size = new Size(0,this.toolbarConnections.Height + this.panBrowserTop.Height + this.panBrowserTop.Height);
						tbbClicked.ToolTipText = "Show Connections and Windows";
					}
					else
					{
						tbbClicked.ImageIndex = MyImageList.getImgList().getImageIndex("UP");
						this.panConnections.Size = new Size(0, this.panBrowser.Height/3);
						tbbClicked.ToolTipText = "Hide Connections and Windows";
					}
					break;
				case "ConBrwExpandAll" : this.tvConnections.ExpandAll(); break;
				case "ConBrwCollapseAll" : this.tvConnections.CollapseAll(); break;
			};
		}
/***************************************************************************/
		private void cmiDBObjsCollapse_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked != null)
			{
				tnRightClicked.Collapse();
				tnRightClicked = null;
			}
		}
/***************************************************************************/
		private void cmiDBObjsExpand_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked != null)
			{
				tnRightClicked.Expand();
				tnRightClicked = null;
			}
		}
/***************************************************************************/
		private void cmiConExpand_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked != null)
			{
				tnRightClicked.Expand();
				tnRightClicked = null;
			}
		}
/***************************************************************************/
		private void cmiConCollapse_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked != null)
			{
				tnRightClicked.Collapse();
				tnRightClicked = null;
			}
		}
/***************************************************************************/
		private void cbSchemas_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			if (isSchemaChanged)
			{
				if (this.cbSchemas.Items[this.cbSchemas.SelectedIndex].ToString().ToUpper().Equals("--Add New Schema--".ToUpper()))
				{
					SchemaNamePrompt frm = new SchemaNamePrompt(this, true);
					frm.ShowDialog();
					this.cbSchemas.SelectedItem = this.cbSchemas.Items[this.cbSchemas.Items.Count - 2];
					return;
				}
				ConnectionTreeNode conTN = this.getActiveConnection();
				if (conTN == null)
					return;
				
				this.tvDBObjects.Nodes.Clear();
				this.tvDBObjects.Nodes.Add(conTN.database.getTreeViewOfSchema(this.cbSchemas.Items[this.cbSchemas.SelectedIndex].ToString()));

				this.tvDBObjects.Nodes[0].Expand();
//				for (int i=0; i < this.tvDBObjects.Nodes[0].Nodes.Count; i++)
//				{
//					this.tvDBObjects.Nodes[0].Nodes[i].Collapse();
//				}
			}
		}
/***************************************************************************/
		private void miSQLWindow_Click(object sender, System.EventArgs e)
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
					MessageBox.Show("The database connection is lost. Please Reconnect and try again.","DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				}
				else //connection is active. allow user to open any new SQL window
				{
					string sqlWindowName = "Sql Window " + (activeConNode.getWindowCount("SQL WINDOW") + 1);
					string tabName = activeConNode.Text + " - " + sqlWindowName;
					int imgIndex = MyImageList.getImgList().getImageIndex("SQL_WINDOW");
					SQLWindowPanel sqlPan = new SQLWindowPanel(activeConNode.database, getActiveSchema(), tabName);
					sqlPan.myTabPage = addTabPage(activeConNode,sqlPan,sqlWindowName,imgIndex);
					//sqlPan.Size = new Size(sqlPan.myTabPage.Width, sqlPan.myTabPage.Height);

//					TreeNode sqlTN = new TreeNode(sqlWindowName, 
//											MyImageList.getImgList().getImageIndex("SQL_WINDOW"),
//											MyImageList.getImgList().getImageIndex("SQL_WINDOW"));
//					activeConNode.Nodes.Add(sqlTN);
//					
//					TabPage tp = new TabPage(text);
//					tp.ImageIndex = MyImageList.getImgList().getImageIndex("SQL_WINDOW");
//					tp.AutoScroll = true;
//
//					sqlTN.Tag = tp;
//					tp.Tag = sqlTN;
//
//					this.tabWindows.TabPages.Add(tp);
//					activeConNode.addWindow(tp);

					//this.tabWindows.SelectedTab = tp;
//					this.tvConnections.SelectedNode = sqlTN;
				}
			}
			else
			{
				MessageBox.Show("Please select an active database connection and try again.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
			}
		}
/***************************************************************************/
		private void tabWindows_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			//user has selected different tab
			TabControl tc = (TabControl)sender;
			TabPage tpgSelected = tc.SelectedTab;
			if (tpgSelected != null)
			{
				int index = tpgSelected.Text.IndexOf("-");
				//take text before "-" as the connection name e.g. user1@database1
				string con = tpgSelected.Text.Substring(0, index).Trim();
				//take text after "-" as the window name e.g. Edit TABLE")1
				string win = tpgSelected.Text.Substring(index + 1).Trim();
				//ConnectionTreeNode conTN = (ConnectionTreeNode)getConnectionNode(con);
				TreeNode winTN = getWindowNode(con, win);
				if (winTN != null)
				{
					this.tvConnections.SelectedNode = winTN;
					this.tvConnections.Select();
				}
			}
		}
/***************************************************************************/
		private TreeNode getConnectionNode(string con)
		{
			for (int i=0; i < this.tvConnections.Nodes.Count; i++)
			{
				if (this.tvConnections.Nodes[i].Text.Equals(con))
					return this.tvConnections.Nodes[i];
			}
			return null;
		}
/***************************************************************************/
		private TreeNode getWindowNode(string con, string win)
		{
			for (int i=0; i < this.tvConnections.Nodes.Count; i++)
			{
				for (int j=0; j < this.tvConnections.Nodes[i].Nodes.Count; j++)
				{
					if (this.tvConnections.Nodes[i].Text.Equals(con) &&
							this.tvConnections.Nodes[i].Nodes[j].Text.Equals(win))
						return this.tvConnections.Nodes[i].Nodes[j];
				}
			}
			return null;
		}
/***************************************************************************/
		private void miConHist_Click(object sender, System.EventArgs e)
		{
			conHistForm.ShowDialog();
		}
/***************************************************************************/
		private void miSqlnet_Click(object sender, System.EventArgs e)
		{
			frmSqlnet.ShowDialog();
		}
/***************************************************************************/
		private void miTnsnames_Click(object sender, System.EventArgs e)
		{
			frmTnsora.ShowDialog();
		}
/***************************************************************************/
		//returns appropriate message
		public string connectToDatabase(string user, string database, string password)
		{
			//check whether USER") is already connected to the same database
			for (int i=0; i < this.tvConnections.Nodes.Count; i++)
			{
				ConnectionTreeNode conTN = (ConnectionTreeNode)this.tvConnections.Nodes[i];
				if (conTN.database_name.Equals(database) && conTN.username.Equals(user))
				{
					//user is already Connected
					return "ALREADY CONNECTED";
				}
			}
			//else validate USER") and connect
			string validUserStr = validDBs.valdiateUser(database, user, password);
			if (validUserStr.Equals("SUCCESS"))
			{
				//after successful validation, create a connection node, create a SQL window under it and 
				//add it in TreeView) of Connection and Windows. Select SQL Window Node and the SQL Window tab.
				ConnectionTreeNode conTN = new ConnectionTreeNode(database, user, password, 
												MyImageList.getImgList().getImageIndex("CONNECTED"), MyImageList.getImgList().getImageIndex("CONNECTED"), true);
				this.tvConnections.Nodes.Add(conTN);
				

				string sqlWindowName = "Sql Window 1";
				string tabName = conTN.Text + " - " + sqlWindowName;
				int imgIndex = MyImageList.getImgList().getImageIndex("SQL_WINDOW");
				SQLWindowPanel sqlPan = new SQLWindowPanel(conTN.database, getActiveSchema(), tabName);
				sqlPan.myTabPage = addTabPage(conTN,sqlPan,sqlWindowName,imgIndex);

//				//create SQL Tab and select it
//				TreeNode sqlTN = new TreeNode("Sql Window 1", MyImageList.getImgList().getImageIndex("SQL_WINDOW"), MyImageList.getImgList().getImageIndex("SQL_WINDOW"));
//				conTN.Nodes.Add(sqlTN);
//				TabPage tp = new TabPage(user + "@" + database + " - Sql Window 1");
//				tp.ImageIndex = MyImageList.getImgList().getImageIndex("SQL_WINDOW");
//				tp.AutoScroll = true;
//
//				this.tabWindows.TabPages.Add(tp);
//				conTN.addWindow(tp);				//add the tab page to list of windows in ConnectionTreeNode
//				
//				sqlTN.Tag = tp;
//				tp.Tag = sqlTN;
//				this.tabWindows.SelectedTab = tp;

				this.tabWindows.Show();
				this.setMainMenuAfterLogin();
				
				MessageBox.Show("Connected to Database successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				init_schemaBrowser(conTN, database, "");

				//this.panBrowser.Show();
				//MessageBox.Show(this.tabWindows.TabPages.Count.ToString());
//				sqlTN.Tag = tp;
//				tp.Tag = sqlTN;
//				this.tvConnections.SelectedNode = conTN.Nodes[0];	//select SQL window 1

//				MessageBox.Show("The connection is successful. Now, create a connection node, " + 
//								"create a SQL window under it and add it in TreeVIEW") of Connection and Windows. " + 
//								"Select SQL Window Node and the SQL Window tab.", "NOT YET DONE");
			}
			return validUserStr;
		}
/***************************************************************************/
		private TreeNode tnRightClicked;
/***************************************************************************/
		private void cmiDisconnect_Click(object sender, System.EventArgs e)
		{
			if (MessageBox.Show("Disconnect from database, are you sure?", "DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question) 
									== DialogResult.Yes)
			{
				if (tnRightClicked == null)
					return;
				if (!tnRightClicked.GetType().ToString().Equals("DBManager.classes.ConnectionTreeNode"))
					return;
				ConnectionTreeNode conTN = (ConnectionTreeNode)tnRightClicked;
				//this.cmiConCloseAllWins.PerformClick();
				/*--------------------------------------------------------*/
				//Close windows
				IDictionaryEnumerator ie = conTN.myWindows.GetEnumerator();
				for (int i=0; i < conTN.myWindows.Keys.Count; i++)
				{
					ie.MoveNext();
					TabPage tp = (TabPage)conTN.myWindows[ie.Key];
					this.tabWindows.TabPages.Remove(tp);
				}
				conTN.closeAllWindows();
				conTN.Nodes.Clear();
				/*--------------------------------------------------------*/
				this.tvConnections.Nodes.Remove(conTN);
				if (this.tvConnections.Nodes.Count == 0)
				{
					//remove all items from Schema combo box and disable DB Object tree view
					this.cbSchemas.Items.Clear();
					init_schemaBrowser(null, "", "");
					setMainMenuBeforeLogin();
					//this.cbSchemas.Enabled = false;
					//this.tvDBObjects.Enabled = false;
				}
			}
			tnRightClicked = null;
		}
/***************************************************************************/
		private void cmiConCloseAllWins_Click(object sender, System.EventArgs e)
		{
			if (MessageBox.Show("Close all windows for the connection, are you sure?", "DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question) 
										== DialogResult.Yes)
			{
				
				if (tnRightClicked == null)
					return;
				if (!tnRightClicked.GetType().ToString().Equals("DBManager.classes.ConnectionTreeNode"))
					return;
				ConnectionTreeNode conTN = (ConnectionTreeNode)tnRightClicked;
				IDictionaryEnumerator ie = conTN.myWindows.GetEnumerator();
				for (int i=0; i < conTN.myWindows.Keys.Count; i++)
				{
					ie.MoveNext();
					TabPage tp = (TabPage)conTN.myWindows[ie.Key];
					this.tabWindows.TabPages.Remove(tp);
				}
				conTN.closeAllWindows();
				conTN.Nodes.Clear();
			}
			tnRightClicked = null;
		}
/***************************************************************************/
		private void cmiNewDBObj_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked == null)
				return;
			if (tnRightClicked.Text.ToUpper().IndexOf("DB LINK") >= 0)
			{
				this.miNewDBLink.PerformClick();
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("FUNCTION") >= 0)
			{
				this.miNewFunction.PerformClick();
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("PROCEDURE") >= 0)
			{
				this.miNewProcedure.PerformClick();
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("SEQUENCE") >= 0)
			{
				this.miNewSequence.PerformClick();
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("TABLE") >= 0)
			{
				this.miNewTable.PerformClick();
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("TRIGGER") >= 0)
			{
				this.miNewTrigger.PerformClick();
			}
			if (tnRightClicked.Text.ToUpper().IndexOf("USER") >= 0)
			{
				this.miNewUser.PerformClick();
//				ConnectionTreeNode conTN = getActiveConnection();
//				if (conTN == null)
//				{
//					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
//					return;
//				}
//				int imgIndex = MyImageList.getImgList().getImageIndex("USER");
//				UserPanel userPanel = new UserPanel(conTN.database, 
//										getActiveSchema(), "", conTN.username);
//				
//				userPanel.myTabPage = addTabPage(conTN, userPanel, "New User " + (conTN.getWindowCount("NEW USER") + 1), imgIndex);
			}
			else if (tnRightClicked.Text.ToUpper().IndexOf("VIEW") >= 0)
			{
				this.miNewView.PerformClick();
			}
			tnRightClicked = null;
		}
/***************************************************************************/
		public TabPage addTabPage(ConnectionTreeNode conTN, Panel panel, 
								string winNodeText, int imgIndex)
		{
			//create SQL Tab and select it
			TreeNode winNode = new TreeNode(winNodeText, imgIndex, imgIndex);
			conTN.Nodes.Add(winNode);
			TabPage tp = new TabPage(conTN.Text + " - " + winNodeText);
			tp.ImageIndex = imgIndex;
			tp.AutoScroll = true;

			tp.Controls.Add(panel);
			this.tabWindows.TabPages.Add(tp);

			winNode.Tag = tp;
			tp.Tag = winNode;

			conTN.addWindow(tp);				//add the tab page to list of windows in ConnectionTreeNode
			this.tabWindows.SelectedTab = tp;
			this.tabWindows.Show();
			this.setMainMenuAfterLogin();
			return tp;
			//init_schemaBrowser(conTN, database, "");
		}
/***************************************************************************/
		public ConnectionTreeNode getActiveConnection()
		{
			if (this.tvConnections.SelectedNode != null)
			{
				if (this.tvConnections.SelectedNode.GetType().ToString().Equals("DBManager.classes.ConnectionTreeNode"))
				{
					return (ConnectionTreeNode)this.tvConnections.SelectedNode;
				}
				else
				{
					return (ConnectionTreeNode)this.tvConnections.SelectedNode.Parent;
				}
			}
			else
			{
				if (this.tvConnections.Nodes.Count > 0)
					return (ConnectionTreeNode)this.tvConnections.Nodes[0];	//return first connection node
			}
			return null;
		}
/***************************************************************************/
		public string getActiveSchema()
		{
			if (this.cbSchemas.Items.Count != 0)
			{
				return (string)this.cbSchemas.SelectedItem;
			}
			return null;
		}
/***************************************************************************/
		private void cmiCloseWindow_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked == null)
				return;

			TabPage tp = (TabPage)tnRightClicked.Tag;
			//(TabControl) tc = tp.Parent();
			ConnectionTreeNode conTN = (ConnectionTreeNode)tnRightClicked.Parent;
			conTN.Nodes.Remove(tnRightClicked);

			//remove the tab page now
			conTN.closeWindow(tp);
		}
/***************************************************************************/
		private void cmiShowWindow_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked == null)
				return;

			TabPage tp = (TabPage)tnRightClicked.Tag;
			this.tabWindows.SelectedTab = tp;

		}
/***************************************************************************/		
		public bool addSchema(string schema_name)
		{
			this.cbSchemas.Items.Remove("--Add New Schema--");
			this.cbSchemas.Items.Add(schema_name);
			this.cbSchemas.Items.Add("--Add New Schema--");
			this.getActiveConnection().database.addSchema(schema_name);
			return true;
		}
/***************************************************************************/
		public TreeNode getDBObjectNode(string dbObjType)
		{
			for (int i=0; i < this.tvDBObjects.Nodes[0].Nodes.Count; i++)
			{
				if (this.tvDBObjects.Nodes[0].Nodes[i].Text.ToString().ToUpper().IndexOf(dbObjType.ToUpper()) >= 0)
				{
					return this.tvDBObjects.Nodes[0].Nodes[i];
				}
			}
			return null;
		}
/***************************************************************************/
		public TreeNode getDBObject(string dbObjType, string dbObjName)
		{
			for (int i=0; i < this.tvDBObjects.Nodes[0].Nodes.Count; i++)
			{
				if (this.tvDBObjects.Nodes[0].Nodes[i].Text.ToString().ToUpper().IndexOf(dbObjType.ToUpper()) >= 0)
				{
					for (int j=0; j < this.tvDBObjects.Nodes[0].Nodes[i].Nodes.Count; j++)
					{
						if (this.tvDBObjects.Nodes[0].Nodes[i].Nodes[j].Text.ToString().ToUpper().Equals(dbObjType.ToUpper()))
							return this.tvDBObjects.Nodes[0].Nodes[i].Nodes[j];
					}
					break;
				}
			}
			return null;
		}
/***************************************************************************/
		private void cmiEditDBObj_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked == null)
				return;
			if (tnRightClicked.Tag.ToString().ToUpper().Equals("DBLINK"))
			{
				this.miEditDBLink.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("FUNCTION"))
			{
				this.miEditFunction.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("PROCEDURE"))
			{
				this.miEditProcedure.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("SEQUENCE"))
			{
				this.miEditSequence.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("TABLE"))
			{
				this.miEditTable.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("TRIGGER"))
			{
				this.miEditTrigger.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("USER"))
			{
				this.miEditUser.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("VIEW"))
			{
				this.miEditView.PerformClick();
			}
			tnRightClicked = null;
		}
/***************************************************************************/
		private void cmiDropDBObj_Click(object sender, System.EventArgs e)
		{
			if (tnRightClicked == null)
				return;
			if (tnRightClicked.Tag.ToString().ToUpper().Equals("DBLINK"))
			{
				this.miDropDBLink.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("FUNCTION"))
			{
				this.miDropFunction.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("PROCEDURE"))
			{
				this.miDropProcedure.PerformClick();	
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("SEQUENCE"))
			{
				this.miDropSequence.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("TABLE"))
			{
				this.miDropTable.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("TRIGGER"))
			{
				this.miDropTrigger.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("USER"))
			{
				this.miDropUser.PerformClick();
			}
			else if (tnRightClicked.Tag.ToString().ToUpper().Equals("VIEW"))
			{
				this.miDropView.PerformClick();
			}
			tnRightClicked = null;
		}
/***************************************************************************/
		private void miFindAllObjs_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "ALL", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, 
										"Find DataBase Objects " + 
											(conTN.getWindowCount("Find DataBase Objects") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindTable_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "TABLE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Tables " + 
											(conTN.getWindowCount("Find Tables") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindView_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "VIEW", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Views " + 
									(conTN.getWindowCount("Find Views") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindSequence_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "SEQUENCE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Sequences " +
												(conTN.getWindowCount("Find Sequences") + 1),
											imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindTrigger_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "TRIGGER", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Triggers " + 
									(conTN.getWindowCount("Find Triggers") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindProcedure_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "PROCEDURE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Procedures " + 
												(conTN.getWindowCount("Find Procedures") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindInProcCode_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "PROCEDURE_CODE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find In Procedure Code " + 
										(conTN.getWindowCount("Find In Procedure Code") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindFunction_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "FUNCTION", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Functions " + 
											(conTN.getWindowCount("Find Functions") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindInFunctCode_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "FUNCTION_CODE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find In Function Code " + 
											(conTN.getWindowCount("Find In Function Code") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miHelpAbout_Click(object sender, System.EventArgs e)
		{
			AboutForm af = new AboutForm();
			af.ShowDialog();
		}
/***************************************************************************/
		private void miNewTable_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("TABLE");
			TablePanel tablePanel = new TablePanel(conTN.database, getActiveSchema(), "", conTN.username);
			
			tablePanel.myTabPage = addTabPage(conTN, tablePanel, "New Table " + (conTN.getWindowCount("NEW TABLE") + 1), imgIndex);
		}
/***************************************************************************/
		private void miNewSequence_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("SEQUENCE");
			SequencePanel seqPanel = new SequencePanel(conTN.database, 
										getActiveSchema(), "", conTN.username);
			
			seqPanel.myTabPage = addTabPage(conTN, seqPanel, "New Sequence " + (conTN.getWindowCount("NEW SEQUENCE") + 1), imgIndex);
		}
/***************************************************************************/
		private void miNewDBLink_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("DBLINK");
			DBLinkPanel dblPanel = new DBLinkPanel(conTN.database, getActiveSchema(), "", conTN.username);
			dblPanel.myTabPage = addTabPage(conTN, dblPanel, "New DB Link " + (conTN.getWindowCount("NEW DBLINK") + 1), imgIndex);
		}
/***************************************************************************/
		private void miNewTrigger_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			//show here dialog to enter trigger name
			ObjectNamePrompt frm = new ObjectNamePrompt(this, true, "Trigger");
			frm.ShowDialog();

			if (!frm.cancelClicked)
			{
				string trigName = frm.txtObjectName.Text;
				int imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_TRIGGER");

				//add the trigger node in tree view
				TreeNode tn = this.getDBObjectNode("TRIGGERS");
				
				if (tn != null)
				{
					string trigSql = "create or replace trigger " + trigName + " as \n" + 
						"declare \n" + 
						"begin \n" +
						"end " + trigName + ";";
					this.getActiveConnection().database.getSchema(this.getActiveSchema()).
						addTrigger(trigName, "", "", trigName, this.getActiveConnection().username, 
									DateTime.Now, trigSql);
						
					TreeNode trigNode = new TreeNode(trigName, imgIndex, imgIndex);
					trigNode.Tag = "TRIGGER";
					tn.Nodes.Add(trigNode);
					tn.Expand();
					tn.Text = "Triggers (" + this.getActiveConnection().database.getSchema(this.getActiveSchema()).procedures.Count + ")";

					if (this.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Trigger added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					}
					else
					{
						MessageBox.Show("Could not add Trigger.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
					MyEditorPanel editorPanel = new MyEditorPanel(conTN.database, getActiveSchema(), trigName, conTN.username, "Trigger", this.imgListMain, trigNode, trigSql);
					editorPanel.myTabPage = addTabPage(conTN, editorPanel, "New Trigger " + (conTN.getWindowCount("NEW TRIGGER") + 1), imgIndex);
				}
			}
		}
/***************************************************************************/
		private void miNewFunction_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			//show here dialog to enter function name
			ObjectNamePrompt frm = new ObjectNamePrompt(this, true, "Function");
			frm.ShowDialog();

			if (!frm.cancelClicked)
			{
				string funcName = frm.txtObjectName.Text;
				int imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_FUNCTION");

				//add the function node in tree view
				TreeNode tn = this.getDBObjectNode("FUNCTIONS");
				
				if (tn != null)
				{
					
					string funcSql = "create or replace function " + funcName + " as \n" + 
										"declare \n" + 
										"begin \n" +
										"end " + funcName + ";";
					this.getActiveConnection().database.getSchema(this.getActiveSchema()).
						addFunction(funcName, this.getActiveConnection().username,DateTime.Now, funcSql);
						
					TreeNode funcNode = new TreeNode(funcName, imgIndex, imgIndex);
					funcNode.Tag = "FUNCTION";
					tn.Nodes.Add(funcNode);
					tn.Expand();
					tn.Text = "Functions (" + this.getActiveConnection().database.getSchema(this.getActiveSchema()).functions.Count + ")";

					if (this.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Function added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					}
					else
					{
						MessageBox.Show("Could not add Function.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
					MyEditorPanel editorPanel = new MyEditorPanel(conTN.database, getActiveSchema(), funcName, conTN.username, "Function", this.imgListMain, funcNode, funcSql);
					editorPanel.myTabPage = addTabPage(conTN, editorPanel, "New Function " + (conTN.getWindowCount("NEW FUNCTION") + 1), imgIndex);
				}
			}
		}
/***************************************************************************/
		private void miNewProcedure_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			//show here dialog to enter function name
			ObjectNamePrompt frm = new ObjectNamePrompt(this, true, "Procedure");
			frm.ShowDialog();

			if (!frm.cancelClicked)
			{
				string procName = frm.txtObjectName.Text;
				int imgIndex = MyImageList.getImgList().getImageIndex("COMPILED_PROCEDURE");

				//add the function node in tree view
				TreeNode tn = this.getDBObjectNode("PROCEDURES");
				
				if (tn != null)
				{
					
					string procSql = "create or replace procedure " + procName + " as \n" + 
						"declare \n" + 
						"begin \n" +
						"end " + procName + ";";
					this.getActiveConnection().database.getSchema(this.getActiveSchema()).
						addProcedure(procName, this.getActiveConnection().username, DateTime.Now, procSql);
						
					TreeNode procNode = new TreeNode(procName, imgIndex, imgIndex);
					procNode.Tag = "PROCEDURE";
					tn.Nodes.Add(procNode);
					tn.Expand();
					tn.Text = "Procedures (" + this.getActiveConnection().database.getSchema(this.getActiveSchema()).procedures.Count + ")";

					if (this.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("Procedure added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					}
					else
					{
						MessageBox.Show("Could not add Procedure.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
					MyEditorPanel editorPanel = new MyEditorPanel(conTN.database, getActiveSchema(), procName, conTN.username, "Procedure", this.imgListMain, procNode, procSql);
					editorPanel.myTabPage = addTabPage(conTN, editorPanel, "New Procedure " + (conTN.getWindowCount("NEW PROCEDURE") + 1), imgIndex);
				}
			}
		}
/***************************************************************************/
		private void miNewView_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			//show here dialog to enter function name
			ObjectNamePrompt frm = new ObjectNamePrompt(this, true, "View");
			frm.ShowDialog();

			if (!frm.cancelClicked)
			{
				string viewName = frm.txtObjectName.Text;
				int imgIndex = MyImageList.getImgList().getImageIndex("VIEW");

				//add the view node in tree view
				TreeNode tn = this.getDBObjectNode("VIEWS");
				if (tn != null)
				{
					string viewSql = "create or replace view " + viewName + " as \n" + 
										"declare \n" + 
										"begin \n" +
										"end " + viewName + ";";
					this.getActiveConnection().database.getSchema(this.getActiveSchema()).
						addView(viewName, this.getActiveConnection().username, DateTime.Now, viewSql);
						
					TreeNode viewNode = new TreeNode(viewName, imgIndex, imgIndex);
					viewNode.Tag = "VIEW";
					tn.Nodes.Add(viewNode);
					tn.Expand();
					tn.Text = "Views (" + this.getActiveConnection().database.getSchema(this.getActiveSchema()).views.Count + ")";

					if (this.getActiveConnection().saveDatabase())
					{
						MessageBox.Show("View added successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
					}
					else
					{
						MessageBox.Show("Could not add View.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
					MyEditorPanel editorPanel = new MyEditorPanel(conTN.database, getActiveSchema(), viewName, conTN.username, "View", this.imgListMain, viewNode, viewSql);
					editorPanel.myTabPage = addTabPage(conTN, editorPanel, "New View " + (conTN.getWindowCount("NEW VIEW") + 1), imgIndex);
				}
			}
		}
/***************************************************************************/
		private void miNewUser_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("USER");
			UserPanel userPanel = new UserPanel(conTN.database, 
				getActiveSchema(), "", conTN.username);
				
			userPanel.myTabPage = addTabPage(conTN, userPanel, "New User " + (conTN.getWindowCount("NEW USER") + 1), imgIndex);
		}
/***************************************************************************/
		private void tvDBObjects_AfterSelect(object sender, System.Windows.Forms.TreeViewEventArgs e)
		{
			//activate / de-activate the items from Main Menu
			TreeView tvCon = (TreeView)sender;
			TreeNode node = e.Node;
			string tag = (string)node.Tag;
			if (tag == null)
			{
				deActivateObjectsMenu();
				return;
			}
			if (tag.ToUpper().IndexOf("TABLE") >= 0)	//user has selected a table node
			{
				deActivateObjectsMenu();
				this.miObjTable.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("SEQUENCE") >= 0)
			{
				deActivateObjectsMenu();
				this.miObjSequence.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("FUNCTION") >= 0)	//user has selected a function node
			{
				deActivateObjectsMenu();
				this.miObjFunction.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("PROCEDURE") >= 0)
			{
				deActivateObjectsMenu();
				this.miObjProcedure.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("VIEW") >= 0)	//user has selected a function node
			{
				deActivateObjectsMenu();
				this.miObjView.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("DBLINK") >= 0)	//user has selected a db link node
			{
				deActivateObjectsMenu();
				this.miObjDBLink.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("TRIGGER") >= 0)
			{
				deActivateObjectsMenu();
				this.miObjTrigger.Enabled = true;
			}
			else if (tag.ToUpper().IndexOf("USER") >= 0)
			{
				deActivateObjectsMenu();
				this.miObjUser.Enabled = true;
			}
			else
			{
				deActivateObjectsMenu();
			}
		}
/***************************************************************************/
		public void deActivateObjectsMenu()
		{
			//de-activate all menu items for DB Objects
			this.miObjDBLink.Enabled = false;
			this.miObjFunction.Enabled = false;
			this.miObjProcedure.Enabled = false;
			this.miObjSequence.Enabled = false;
			this.miObjTable.Enabled = false;
			this.miObjTrigger.Enabled = false;
			this.miObjUser.Enabled = false;
			this.miObjView.Enabled = false;
		}
/***************************************************************************/
		private void miEditDBLink_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a DB Link first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string dblinkName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit DB Link - " + dblinkName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			if (win == null)
			{
				int imgIndex = MyImageList.getImgList().getImageIndex("DBLINK");
				DBLinkPanel dblinkPanel = new DBLinkPanel(conTN.database, getActiveSchema(), 
												dblinkName, conTN.username);

				dblinkPanel.myTabPage = addTabPage(conTN, dblinkPanel, "Edit DB Link - " + 
											dblinkName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropDBLink_Click(object sender, System.EventArgs e)
		{
			TreeNode dbLinkNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a DB Link first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the DB Link '" + dbLinkNode.Text + "', are you sure?", 
				"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				//drop the db link
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string dbLinkName = dbLinkNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteDBLink(dbLinkName))
				{
					TreeNode tn = (TreeNode)dbLinkNode.Parent;
					tn.Nodes.Remove(dbLinkNode);
					tn.Text = "DB Links (" + conTN.database.getSchema(schema_name).dblinks.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this DB Link
					MessageBox.Show("DB Link deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete DB Link.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditFunction_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Function first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string funcName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit Function - " + funcName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			Function f = conTN.database.getFunction(this.getActiveSchema(), funcName);
			if (win == null)
			{
				int imgIndex = (f.isCompiled) ? MyImageList.getImgList().getImageIndex("COMPILED_FUNCTION") :
												MyImageList.getImgList().getImageIndex("FUNCTION_WITH_ERRORS");
				MyEditorPanel funcPanel = new MyEditorPanel(conTN.database, getActiveSchema(), 
								funcName, conTN.username, "Function", this.imgListMain, this.tvDBObjects.SelectedNode, "");

				funcPanel.myTabPage = addTabPage(conTN, funcPanel, "Edit Function - " + 
													funcName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropFunction_Click(object sender, System.EventArgs e)
		{
			TreeNode funcNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Function first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the Function '" + funcNode.Text + "', are you sure?", 
						"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
					== DialogResult.Yes)
			{
				//drop the db link
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string funcName = funcNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteFunction(funcName))
				{
					TreeNode tn = (TreeNode)funcNode.Parent;
					tn.Nodes.Remove(funcNode);
					tn.Text = "Functions (" + conTN.database.getSchema(schema_name).functions.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this DB Link
					MessageBox.Show("Function deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete Function.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditProcedure_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Procedure first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string procName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit Procedure - " + procName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			Procedure p = conTN.database.getProcedure(this.getActiveSchema(), procName);
			if (win == null)
			{
				int imgIndex = (p.isCompiled) ? MyImageList.getImgList().getImageIndex("COMPILED_PROCEDURE") :
								MyImageList.getImgList().getImageIndex("PROCEDURE_WITH_ERRORS");
				
				MyEditorPanel procPanel = new MyEditorPanel(conTN.database, getActiveSchema(), 
					procName, conTN.username, "Procedure", this.imgListMain, this.tvDBObjects.SelectedNode, "");

				procPanel.myTabPage = addTabPage(conTN, procPanel, "Edit Procedure - " + 
								procName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropProcedure_Click(object sender, System.EventArgs e)
		{
			TreeNode procNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Procedure first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the Procedure '" + procNode.Text + "', are you sure?", 
				"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				//drop the db link
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string procName = procNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteProcedure(procName))
				{
					TreeNode tn = (TreeNode)procNode.Parent;
					tn.Nodes.Remove(procNode);
					tn.Text = "Procedures (" + conTN.database.getSchema(schema_name).procedures.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this DB Link
					MessageBox.Show("Procedure deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete Procedure.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditSequence_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Sequence first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string seqName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit Sequence - " + seqName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			if (win == null)
			{
				int imgIndex = MyImageList.getImgList().getImageIndex("SEQUENCE");
				
				SequencePanel seqPanel = new SequencePanel(conTN.database, getActiveSchema(), 
											seqName, conTN.username);

				seqPanel.myTabPage = addTabPage(conTN, seqPanel, "Edit Sequence - " + seqName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropSequence_Click(object sender, System.EventArgs e)
		{
			TreeNode seqNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Sequence first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the Sequence '" + seqNode.Text + "', are you sure?", 
						"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string seqName = seqNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteSequence(seqName))
				{
					TreeNode tn = (TreeNode)seqNode.Parent;
					tn.Nodes.Remove(seqNode);
					tn.Text = "Sequences (" + conTN.database.getSchema(schema_name).sequences.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this DB Link
					MessageBox.Show("Sequence deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete Sequence.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditView_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a View first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string viewName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit View - " + viewName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			if (win == null)
			{
				int imgIndex = MyImageList.getImgList().getImageIndex("VIEW");
				
				MyEditorPanel viewPanel = new MyEditorPanel(conTN.database, getActiveSchema(), 
											viewName, conTN.username, "View", this.imgListMain, this.tvDBObjects.SelectedNode, "");

				viewPanel.myTabPage = addTabPage(conTN, viewPanel, "Edit View - " + viewName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropView_Click(object sender, System.EventArgs e)
		{
			TreeNode viewNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a View first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the View '" + viewNode.Text + "', are you sure?", 
				"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				//drop the view
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string viewName = viewNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteView(viewName))
				{
					TreeNode tn = (TreeNode)viewNode.Parent;
					tn.Nodes.Remove(viewNode);
					tn.Text = "Views (" + conTN.database.getSchema(schema_name).views.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this DB Link
					MessageBox.Show("View deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete View.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditTrigger_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Trigger first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string trigName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit Trigger - " + trigName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			Trigger t = conTN.database.getTrigger(this.getActiveSchema(), trigName);
			if (win == null)
			{
				int imgIndex = (t.isCompiled) ? MyImageList.getImgList().getImageIndex("COMPILED_TRIGGER") :
					MyImageList.getImgList().getImageIndex("TRIGGER_WITH_ERRORS");
				
				MyEditorPanel trigPanel = new MyEditorPanel(conTN.database, getActiveSchema(), 
					trigName, conTN.username, "Trigger", this.imgListMain, this.tvDBObjects.SelectedNode, "");

				trigPanel.myTabPage = addTabPage(conTN, trigPanel, "Edit Trigger - " + 
					trigName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miDropTrigger_Click(object sender, System.EventArgs e)
		{
			TreeNode trigNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Trigger first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the Trigger '" + trigNode.Text + "', are you sure?", 
								"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				//drop the view
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string trigName = trigNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteTrigger(trigName))
				{
					TreeNode tn = (TreeNode)trigNode.Parent;
					tn.Nodes.Remove(trigNode);
					tn.Text = "Triggers (" + conTN.database.getSchema(schema_name).triggers.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this Trigger
					MessageBox.Show("Trigger deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete Trigger.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miFindDBLink_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "DBLINK", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, 
									"Find DB Links " + (conTN.getWindowCount("Find DB Links") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindInTriggerCode_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "TRIGGER_CODE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find In Trigger Code " + 
									(conTN.getWindowCount("Find In Trigger Code") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindInViewCode_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "VIEW_CODE", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find In View Code " + 
												(conTN.getWindowCount("Find In View Code") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miFindUser_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (conTN == null)
			{
				MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			int imgIndex = MyImageList.getImgList().getImageIndex("FIND");
			MyFindPanel findPanel = new MyFindPanel(conTN.database, getActiveSchema(), "USER", this.imgListMain);
				
			findPanel.myTabPage = addTabPage(conTN, findPanel, "Find Users " + 
									(conTN.getWindowCount("Find Users") + 1), imgIndex);
			findPanel.textBox1.Select();
			findPanel.textBox1.Focus();
		}
/***************************************************************************/
		private void miDropTable_Click(object sender, System.EventArgs e)
		{
			TreeNode tableNode = this.tvDBObjects.SelectedNode;
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Table first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			if (MessageBox.Show("Drop the Table '" + tableNode.Text + "', are you sure?", 
						"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
						== DialogResult.Yes)
			{
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				string tableName = tableNode.Text;
				string schema_name = this.getActiveSchema();
				if (conTN.database.getSchema(schema_name).deleteTable(tableName))
				{
					TreeNode tn = (TreeNode)tableNode.Parent;
					tn.Nodes.Remove(tableNode);
					tn.Text = "Tables (" + conTN.database.getSchema(schema_name).tables.Count + ")";
					conTN.saveDatabase();
					//close all windows that are open for this Table
					MessageBox.Show("Table deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete Table.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
		private void miEditTable_Click(object sender, System.EventArgs e)
		{
			ConnectionTreeNode conTN = getActiveConnection();
			if (this.tvDBObjects.SelectedNode == null)
			{
				MessageBox.Show("Please select a Table first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
				return;
			}
			string tableName = this.tvDBObjects.SelectedNode.Text;
			string winTabPageName = conTN.Text + " - " + "Edit Table - " + tableName;
			TabPage win = conTN.getWindowByName(winTabPageName);
			if (win == null)
			{
				int imgIndex = MyImageList.getImgList().getImageIndex("TABLE");
				
				TablePanel tablePanel = new TablePanel(conTN.database, getActiveSchema(), 
												tableName, conTN.username);
				tablePanel.myTabPage = addTabPage(conTN, tablePanel, "Edit Table - " + tableName, imgIndex);
			}
			else
			{
				this.tabWindows.SelectedTab = win;
			}
		}
/***************************************************************************/
		private void miEditUser_Click(object sender, System.EventArgs e)
		{
//			ConnectionTreeNode conTN = getActiveConnection();
//			if (this.tvDBObjects.SelectedNode == null)
//			{
//				MessageBox.Show("Please select a Trigger first.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
//				return;
//			}
//			string trigName = this.tvDBObjects.SelectedNode.Text;
//			string winTabPageName = conTN.Text + " - " + "Edit Trigger - " + trigName;
//			TabPage win = conTN.getWindowByName(winTabPageName);
//			Trigger t = conTN.database.getTrigger(this.getActiveSchema(), trigName);
//			if (win == null)
//			{
//				int imgIndex = (t.isCompiled) ? MyImageList.getImgList().getImageIndex("COMPILED_TRIGGER") :
//					MyImageList.getImgList().getImageIndex("TRIGGER_WITH_ERRORS");
//				
//				MyEditorPanel trigPanel = new MyEditorPanel(conTN.database, getActiveSchema(), 
//					trigName, conTN.username, "Trigger", this.imgListMain, this.tvDBObjects.SelectedNode, "");
//
//				trigPanel.myTabPage = addTabPage(conTN, trigPanel, "Edit Trigger - " + 
//					trigName, imgIndex);
//			}
//			else
//			{
//				this.tabWindows.SelectedTab = win;
//			}
		}
/***************************************************************************/
		private void miDropUser_Click(object sender, System.EventArgs e)
		{
			
		}
/***************************************************************************/
		private void cmiDropAll_Click(object sender, System.EventArgs e)
		{
			TreeNode dbObjNode = this.tvDBObjects.SelectedNode;
			if (dbObjNode == null || 
					dbObjNode.Text.ToUpper().IndexOf("DATABASE OBJECTS") >=0 ||
					!(dbObjNode.Text.ToUpper().IndexOf("(") >=0) )
			{
				return;
			}
			//extract the datebase objects' type to be dropped
			string objType = dbObjNode.Text.Substring(0, dbObjNode.Text.IndexOf("(")).Trim();
			
			if (MessageBox.Show("Drop all " + objType + ", are you sure?", 
				"DB Manager", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
				== DialogResult.Yes)
			{
				ConnectionTreeNode conTN = getActiveConnection();
				if (conTN == null)
				{
					MessageBox.Show("No active connection.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
					return;
				}
				for (int i=0; i < dbObjNode.Nodes.Count; i++)
				{
					string dbObjName = dbObjNode.Nodes[i].Text.Trim();
					switch(dbObjNode.Nodes[i].Tag.ToString().ToUpper())
					{
						case "DBLINK" :		conTN.database.getSchema(getActiveSchema()).deleteDBLink(dbObjName);
											dbObjNode.Text = "DB Links (" + 
												conTN.database.getSchema(getActiveSchema()).dblinks.Count + 
												")";
											break;
						case "FUNCTION" :	conTN.database.getSchema(getActiveSchema()).deleteFunction(dbObjName);
											dbObjNode.Text = "Functions (" + 
												conTN.database.getSchema(getActiveSchema()).functions.Count + 
												")";
											break;
						case "PROCEDURE" :	conTN.database.getSchema(getActiveSchema()).deleteProcedure(dbObjName);
											dbObjNode.Text = "Procedures (" + 
												conTN.database.getSchema(getActiveSchema()).procedures.Count + 
												")";
											break;
						case "SEQUENCE" :	conTN.database.getSchema(getActiveSchema()).deleteSequence(dbObjName);
											dbObjNode.Text = "Sequences (" + 
												conTN.database.getSchema(getActiveSchema()).sequences.Count + 
												")";
											break;
						case "TABLE" :		conTN.database.getSchema(getActiveSchema()).deleteTable(dbObjName);
											dbObjNode.Text = "Tables (" + 
												conTN.database.getSchema(getActiveSchema()).tables.Count + 
												")";
											break;
						case "TRIGGER" :	conTN.database.getSchema(getActiveSchema()).deleteTrigger(dbObjName);
											dbObjNode.Text = "Triggers (" + 
												conTN.database.getSchema(getActiveSchema()).triggers.Count + 
												")";
											break;
//						case "USER" :		conTN.database.getSchema(getActiveSchema()).deleteUser(dbObjName);
//											dbObjNode.Text = "Users (" + 
//												conTN.database.getSchema(getActiveSchema()).users.Count + 
//												")";
											break;
						case "VIEW" :		conTN.database.getSchema(getActiveSchema()).deleteView(dbObjName);
											dbObjNode.Text = "Views (" + 
												conTN.database.getSchema(getActiveSchema()).views.Count + 
												")";
											break;
					};
				}
				if (conTN.saveDatabase())
				{
					dbObjNode.Nodes.Clear();
					MessageBox.Show("All " + objType + " deleted successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Could not delete all " + objType + ".", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/
