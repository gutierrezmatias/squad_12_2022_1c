package com.aninfo.psa;

import com.aninfo.psa.listas.ListaDeProyectos;
import com.aninfo.psa.modelo.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyStepdefs {

    Proyecto proyecto;
    String dado_de_baja;

    Recurso recurso;
    private Tarea tarea;

    ListaDeProyectos listaDeProyectos;

    ArrayList<Proyecto> proyectosPedidos;
    private List<Tarea> listaDeTareas;

    List<Tarea> tarea_busqueda_por_estado;
    List<Tarea> tarea_busqueda_por_prioridad;

    @Given("que soy un empleado")
    public void queSoyUnEmpleado() {
    }

    @When("solicite crear un proyecto ingresare: {string},{string},{string},{string},{string},{string}")
    public void soliciteCrearUnProyectoIngresare(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        proyecto = new Proyecto(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    @Then("el sistema creara un proyecto con :{string},{string},{string},{string},{string},{string} sin empleados, tiempo estimado, ni tareas")
    public void elSistemaCrearaUnProyectoConSinEmpleadosTiempoEstimadoNiTareas(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        assertEquals(arg0, proyecto.getNombre());
        assertEquals(arg1, proyecto.getTipo());
        assertEquals(arg2, proyecto.getCliente());
        assertEquals(arg3, proyecto.getAlcance());
        assertEquals(arg4, proyecto.getVersion());
        assertEquals(arg5, proyecto.getDescripcion());
    }

    @When("solicito dar de baja un proyecto")
    public void solicitoDarDeBajaUnProyecto() {
        proyecto = new Proyecto();
        proyecto.dar_baja();
    }

    @Then("el sistema dará de baja ese proyecto")
    public void elSistemaDaráDeBajaEseProyecto() {
        dado_de_baja = proyecto.getEstado();
    }

    @And("su estado cambiará a <{string}>")
    public void suEstadoCambiaráA(String arg0) {
        assertEquals(arg0, dado_de_baja);
    }

    @And("elijo un proyecto")
    public void elijoUnProyecto() {
        proyecto = new Proyecto();
    }

    @When("solicito a recursos humanos los recursos disponibles")
    public void solicitoARecursosHumanosLosRecursosDisponibles() {
        recurso = new Recurso("Juan");
    }

    @Then("el sistema permitira la designación de un empleado como lider de proyecto")
    public void elSistemaPermitiraLaDesignaciónDeUnEmpleadoComoLiderDeProyecto() {
        proyecto.asignar_lider(recurso);
        assertNotNull(proyecto.getLider());
    }

    @Then("el sistema permitira la modificacion del lider del proyecto")
    public void elSistemaPermitiraLaModificacionDelLiderDelProyecto() {
        var recurso_auxiliar = new Recurso("Manuel");
        proyecto.asignar_lider(recurso);
        proyecto.asignar_lider(recurso_auxiliar);
        assertNotEquals(recurso.getNombre(), proyecto.getLider().getNombre());

    }

    @When("elija una tarea sin estimacion de horas")
    public void elijaUnaTareaSinEstimacionDeHoras() {
         tarea = new Tarea();
    }

    @And("ingrese <{int}>")
    public void ingrese(int arg0) {
        tarea.ingresar_estimado(arg0);
    }

    @Then("la tarea pasará a tener <{int}> horas estimadas")
    public void laTareaPasaráATenerHorasEstimadas(int arg0) {
        assertEquals(arg0, tarea.getHorasEstimadas());
    }

    @When("elijo una tarea con {int} horas estimadas")
    public void elijoUnaTareaConHorasEstimadas(int arg0) {
        tarea = new Tarea(arg0);
    }

    @And("modifiquo las horas a <{int}>")
    public void modifiquoLasHorasA(int arg0) {
        tarea.ingresar_estimado(arg0);
    }

    @And("una lista de proyectos creados: {string}, {string}, {string}")
    public void unaListaDeProyectosCreados(String arg0, String arg1, String arg2) {
        listaDeProyectos = new ListaDeProyectos();

        var proyecto1 = new Proyecto(arg0);
        proyecto1.dar_baja();

        var proyecto2 = new Proyecto(arg1);
        var proyecto3 = new Proyecto(arg2);
        listaDeProyectos.add(proyecto1);
        listaDeProyectos.add(proyecto2);
        listaDeProyectos.add(proyecto3);
    }

    @When("solicito los proyectos creados")
    public void solicitoLosProyectosCreados() {
        proyectosPedidos = listaDeProyectos.listado_proyectos();
    }

    @Then("se mostrarán los proyectos: {string}, {string}, {string}")
    public void seMostraránLosProyectos(String arg0, String arg1, String arg2) {
        assertEquals(arg0, proyectosPedidos.stream().filter(x -> arg0.equals(x.getNombre())).findFirst().get().getNombre());
        assertEquals(arg1, proyectosPedidos.stream().filter(x -> arg1.equals(x.getNombre())).findFirst().get().getNombre());
        assertEquals(arg2,proyectosPedidos.stream().filter(x -> arg2.equals(x.getNombre())).findFirst().get().getNombre());
    }

    @And("primero apareceran <{string}> y <{string}>")
    public void primeroApareceranY(String arg0, String arg1) {
        assertEquals(arg0, proyectosPedidos.get(1).getNombre());
        assertEquals(arg1, proyectosPedidos.get(0).getNombre());
    }

    @Given("que hay proyecto con las tareas: {string}, {string}, {string}")
    public void queHayProyectoConLasTareas(String arg0, String arg1, String arg2) {
        proyecto = new Proyecto();
        var tarea1 = new Tarea(arg0);
        var tarea2 = new Tarea(arg1);
        var tarea3 = new Tarea(arg2);


        proyecto.add_tarea(tarea1);
        proyecto.add_tarea(tarea2);
        proyecto.add_tarea(tarea3);
    }

    @When("solicito las tareas de un proyecto determinado")
    public void solicitoLasTareasDeUnProyectoDeterminado() {
        listaDeTareas = proyecto.getTareas();
    }

    @Then("se mostrarán las tareas: {string}, {string}, {string}")
    public void seMostraránLasTareas(String arg0, String arg1, String arg2) {
        assertEquals(arg0, listaDeTareas.get(0).getNombre());
        assertEquals(arg1, listaDeTareas.get(1).getNombre());
        assertEquals(arg2, listaDeTareas.get(2).getNombre());
    }

    @Given("que hay un proyecto con las {string} con el estado {string} y {string} con estado {string}")
    public void queHayUnProyectoConLasConElEstadoYConEstado(String arg0, String arg1, String arg2, String arg3) {
        proyecto = new Proyecto();
        var tarea1 = new Tarea(arg0);
        tarea1.cambiar_estado(arg1);

        var tarea2 = new Tarea(arg3);
        tarea2.cambiar_estado(arg3);

        proyecto.add_tarea(tarea1);
        proyecto.add_tarea(tarea2);
    }

    @When("solicito buscar una tarea {string}")
    public void solicitoBuscarUnaTarea(String arg0) {
        tarea_busqueda_por_estado = proyecto.buscar_tarea_por_estado(arg0);

    }

    @Then("se mostrarán la tarea {string}")
    public void seMostraránLaTarea(String arg0) {
        assertEquals(arg0, tarea_busqueda_por_estado.get(0).getNombre());
    }

    @Given("que hay un proyecto {string} con las tareas {string} con prioridad {string} y otra {string} con prioridad {string}")
    public void queHayUnProyectoConLasTareasConPrioridadYOtraConPrioridad(String arg0, String arg1, String arg2, String arg3, String arg4) {
        proyecto = new Proyecto();

        var tarea1 = new Tarea(arg1);
        tarea1.cambiar_prioridad(arg2);

        var tarea2 = new Tarea(arg3);
        tarea2.cambiar_prioridad(arg4);

        proyecto.add_tarea(tarea1);
        proyecto.add_tarea(tarea2);
    }

    @When("solicito buscar las tareas con prioridad: {string}")
    public void solicitoBuscarLasTareasConPrioridad(String arg0) {
        tarea_busqueda_por_prioridad = proyecto.buscar_tarea_por_prioridad(arg0);


    }

    @Then("se mostrarán las tarea {string}")
    public void seMostraránLasTarea(String arg0) {
        assertEquals(arg0, tarea_busqueda_por_prioridad.get(0).getNombre());
    }
}
