package modelo;


public class Docente extends Persona{
    private int cantHoras;
    private double pagoHora;
    private double salario;

    public int getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public double getPagoHora() {
        return pagoHora;
    }

    public void setPagoHora(double pagoHora) {
        this.pagoHora = pagoHora;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public void calcularSalario() {
        setSalario (getCantHoras() * getPagoHora());
    }
    
    
    public void evaluarAlumno(Alumno objAl, double calMate,
                                            double calfisica,
                                            double calProgra) {
        
        objAl.setCalMate(calMate);
        objAl.setCalFisica(calfisica);
        objAl.setCalProgra(calProgra);
        objAl.obtenerPromedio();
    }
    
    
    @Override
    public String mostrarInfo() {
        String info = super.mostrarInfo();
               info+= "Horas impartidas :" + getCantHoras() + "\n" +
                      "Pago x hora :" + getPagoHora() + "\n" +
                      "Salario :" + getSalario() + "\n";
        return info;
    }
}
