package lib.funcoes
{

	public class DateUtil
	{
		public static function DD_MM_YYYY(data:Date):String
		{
			var dataFormatada:String;

			var ano:int=data.fullYear;
			var mes:int=data.month + 1;
			var dia:int=data.date;

			dataFormatada=(dia + "/" + mes + "/" + ano).toString();

			return dataFormatada;
		}

		public static function MM_DD_YYYY(data:Date):String
		{
			var dataFormatada:String;

			var ano:int=data.fullYear;
			var mes:int=data.month + 1;
			var dia:int=data.date;

			dataFormatada=(mes + "/" + dia + "/" + ano).toString();

			return dataFormatada;
		}

		public static function YYYY_DD_MM(data:Date):String
		{
			var dataFormatada:String;

			var ano:int=data.fullYear;
			var mes:int=data.month + 1;
			var dia:int=data.date;

			dataFormatada=(ano + "/" + dia + "/" + mes).toString();

			return dataFormatada;
		}

		public static function YYYY_MM_DD(data:Date):String
		{
			var dataFormatada:String;

			var ano:int=data.fullYear;
			var mes:int=data.month + 1;
			var dia:int=data.date;

			dataFormatada=(ano + "/" + mes + "/" + dia).toString();

			return dataFormatada;
		}
	}
}