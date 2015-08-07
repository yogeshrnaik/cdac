/***************************************************************************/
using System;
using System.Collections;
using System.Drawing;
/***************************************************************************/
namespace DBManager.classes
{
/***************************************************************************/
	/// <summary>
	/// Summary description for General.
	/// </summary>
/***************************************************************************/
	public class MyImageList
	{
/***************************************************************************/
		private static MyImageList imgList;
		private Hashtable images;
/***************************************************************************/
		private MyImageList()
		{
			images = new Hashtable();
			images.Add(0.ToString(), "NEW_CONNECTION");
			images.Add(1.ToString(), "CONNECTION_HISTORY");
			images.Add(2.ToString(), "SQL_WINDOW");
			images.Add(3.ToString(), "BROWSER");
			images.Add(4.ToString(), "COMMIT");
			images.Add(5.ToString(), "ROLLBACK");
			images.Add(6.ToString(), "FIND");
			images.Add(7.ToString(), "CONNECTED");
			images.Add(8.ToString(), "DISCONNECTED");
			images.Add(9.ToString(), "TABLE");
			images.Add(10.ToString(), "USER");
			images.Add(11.ToString(), "SEQUENCE");
			images.Add(12.ToString(), "VIEW");
			images.Add(13.ToString(), "DBLINK");
			images.Add(14.ToString(), "FOLDER");
			images.Add(15.ToString(), "HELP");
			images.Add(16.ToString(), "COMPILED_FUNCTION");
			images.Add(17.ToString(), "FUNCTION_WITH_ERRORS");
			images.Add(18.ToString(), "COMPILED_PROCEDURE");
			images.Add(19.ToString(), "PROCEDURE_WITH_ERRORS");
			images.Add(20.ToString(), "COMPILED_TRIGGER");
			images.Add(21.ToString(), "TRIGGER_WITH_ERRORS");
			images.Add(22.ToString(), "UP");
			images.Add(23.ToString(), "DOWN");
			images.Add(24.ToString(), "CROSS");
			images.Add(25.ToString(), "EXPAND_ALL");
			images.Add(26.ToString(), "COLLAPSE_ALL");
			images.Add(27.ToString(), "REFRESH");
			images.Add(28.ToString(), "COMPILE");
			images.Add(29.ToString(), "EXECUTE");
			images.Add(30.ToString(), "FONT");
			images.Add(31.ToString(), "SAVE");
			images.Add(32.ToString(), "CLOSE");
		}
/***************************************************************************/
		public static MyImageList getImgList()
		{
			if (imgList == null)
				imgList = new MyImageList();
			return imgList;
		}
/***************************************************************************/
		public int getImageIndex(string imageName)
		{
			if (imageName.Equals("FUNCTION") || 
				imageName.Equals("PROCEDURE") ||
				imageName.Equals("TRIGGER"))
			{
				imageName = "COMPILED_" + imageName;
			}
			IDictionaryEnumerator ie = images.GetEnumerator();
			while(ie.MoveNext())
			{
				if (ie.Value.ToString().Equals(imageName))
					return (Int16.Parse(ie.Key.ToString()));
			}
			return 0;
		}
/***************************************************************************/
		public string getImageName(int imageIndex)
		{
			return (string)images[imageIndex.ToString()];
		}
/***************************************************************************/
		public Icon getIcon(string imageName)
		{
			//string iconsPath  = "E:\\UserData\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\icons\\";
			string iconsPath  = "D:\\MY HDD\\1. Yogesh\\1. My Projects\\Latest-DBManager\\21-current-DBManager\\icons\\";
			string iconName = "";
			switch (imageName)
			{
				case "DBLINK" :					iconName = "dblink.ico"; break;
				case "COMPILED_FUNCTION" :		iconName = "function_compiled.ico"; break;
				case "FUNCTION_WITH_ERRORS" :	iconName = "function_with_errors.ico"; break;
				case "COMPILED_PROCEDURE" :		iconName = "procedure_compiled.ico"; break;
				case "PROCEDURE_WITH_ERRORS" :	iconName = "procedure_with_errors.ico"; break;
				case "SEQUENCE"	:				iconName = "sequence.ico"; break;
				case "TABLE" :					iconName = "table.ico"; break;
				case "COMPILED_TRIGGER" :		iconName = "trigger_compiled.ico"; break;
				case "TRIGGER_WITH_ERRORS" :	iconName = "trigger_with_errors.ico"; break;
				case "USER" :					iconName = "user.ico"; break;
				case "VIEW" :					iconName = "view.ico"; break;
				case "SQL_WINDOW" :				iconName = "sql.ico"; break;
				case "FIND" :					iconName = "find.ico"; break;
				case "CONNECTED" :				iconName = "connected.ico"; break;
				case "DISCONNECTED" :			iconName = "disconnected.ico"; break;
			};
			Icon icon = null;
			if (iconName.Length > 0)
			{
				icon = new Icon(iconsPath + iconName);
			}
			return icon;
		}
/***************************************************************************/
		public Image getImage(string imageName)
		{
			string iconsPath  = "E:\\UserData\\Yogesh-d0331092\\1.DB Manager\\current-DBManager\\icons\\";
			string filename = "";
			switch (imageName)
			{
				case "CONNECTED" : filename = "connected.bmp"; break;
				case "DISCONNECTED" : filename = "disconnected.bmp"; break;
				case "TABLE" : filename = "table.bmp"; break;
				case "USER" : filename = "user.bmp"; break;
				case "CLOSE" : filename = "close.bmp"; break;
			};
			Image img = null;
			if (filename.Length > 0)
			{
				img = Image.FromFile(iconsPath + filename);
			}
			return img;
		}
/***************************************************************************/		
	}
/***************************************************************************/
}
/***************************************************************************/
