package tierraMedia;

public abstract class ModificadorFormatoHora {

	public static String obtenerHoraConFormato(double tiempo) {
		if(tiempo == 0)
			return "0 horas";
		
		String horaConSufijo = "";
		String minutosConSufijo = "";
		String unionTextos = "";

		int horas = (int) tiempo;
		int minutosSinFormato = (int) ((tiempo % 1) * 100);

		if (minutosSinFormato > 0) {
			int minutos = Math.round(minutosSinFormato * 60 / 100);
			if (minutos > 1)
				minutosConSufijo = minutos + " minutos";
			else if (minutos == 1)
				minutosConSufijo = "1 minuto";
		}

		if (horas == 1)
			horaConSufijo = horas + " hora";
		else if (horas > 1)
			horaConSufijo = horas + " horas";

		if (horaConSufijo.length() > 0 && minutosConSufijo.length() > 0)
			unionTextos = " con ";

		return horaConSufijo + unionTextos + minutosConSufijo;
	}

}
