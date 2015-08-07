/***************************************************************************/
using System;
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
		private static ImageList imgList;
/***************************************************************************/
		private MyImageList()
		{
		}
/***************************************************************************/
		public static ImageList getImgList()
		{
			if (imgList == null)
				imgList = new ImageList();
			return imgList;
		}
/***************************************************************************/
		public int getImageIndex(string imageName)
		{
			switch (imageName)
			{
				case "NEW_CONNECTION" : return 0;
				case "CONNECTION_HISTORY" : return 1;
				case "SQL_WINDOW" : return 2;
				case "BROWSER" : return 3;
				case "COMMIT" : return 4;
				case "ROLLBACK" : return 5;
				case "FIND" : return 6;
				case "CONNECTED" : return 7;
				case "DISCONNECTED" : return 8;
				case "TABLE" : return 9;
				case "USER" : return 10;
				case "SEQUENCE" : return 11;
				case "VIEW" : return 12;
				case "DBLINK" : return 13;
				case "FOLDER" : return 14;
				case "HELP" : return 15;
				case "COMPILED_FUNCTION" : return 16;
				case "FUNCTION_WITH_ERRORS" : return 17;
				case "COMPILED_PROCEDURE" : return 18;
				case "PROCEDURE_WITH_ERRORS" : return 19;
				case "COMPILED_TRIGGER" : return 20;
				case "TRIGGER_WITH_ERRORS" : return 21;
				case "UP" : return 22;
				case "DOWN" : return 23;
				case "CROSS" : return 24;
				case "EXPAND_ALL" : return 25;
				case "COLLAPSE_ALL" : return 26;
				case "REFRESH" : return 27;
			};
			return 0;
		}
/***************************************************************************/
		
/***************************************************************************/		
	}
/***************************************************************************/
}
/***************************************************************************/
