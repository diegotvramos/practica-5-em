<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>
            <c:if test="${proveedor.id==0}">Nuevo</c:if> 
            <c:if test="${proveedor.id!=0}" >Editar</c:if>
               Producto
       </h1>
            <form action="inicio" method="post">
                <input type="hidden" name="id" value="${proveedor.id}" />
            <table>
                <tr><td>Descripcion:</td>
                    <td><input type="text" name="nombre" value="${proveedor.nombre}"/></td>
                </tr>
                <tr><td>Stock:</td>
                    <td><input type="number" name="antiguedad" value="${proveedor.antiguedad}"/></td>
                </tr>
                
                
                <tr>
                    <td></td>
                    <td> <input type="submit"></td>
                </tr>

            </table>

        </form>
    </body>
</html>
