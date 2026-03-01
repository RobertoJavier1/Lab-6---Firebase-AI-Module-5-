# Laboratorio 6 - Firebase & AI: Favorites + Sharing

## Parte 1: Recipe Favorites

Se implementó un sistema de recetas favoritas que permite a los usuarios marcar sus recetas preferidas con un ícono de corazón directamente desde la lista. El estado de favorito se sincroniza en tiempo real con Firestore, por lo que persiste entre sesiones. Además, se agregó un filtro en la pantalla de lista para que el usuario pueda alternar entre ver todas sus recetas o únicamente las marcadas como favoritas.

**Video explicación:**

### Definition of Done

| Criterio | Descripción | ✅ |
|----------|-------------|---|
| Model updated | `Recipe` tiene campo `isFavorite: Boolean` | ✅ |
| Firestore sync | `toggleFavorite()` actualiza el documento en Firestore | ✅ |
| Heart icon visible | Cada tarjeta muestra corazón lleno/vacío según estado | ✅ |
| Toggle works | Al tocar el corazón se actualiza el estado y sincroniza con la nube | ✅ |
| Filter exists | El usuario puede cambiar a vista "Solo favoritas" | ✅ |
| Real-time updates | Los cambios se reflejan de inmediato gracias al listener de Firestore | ✅ |
| Optimistic UI | El corazón cambia al instante y revierte si ocurre un error | ✅ |

---

## Parte 2: Recipe Sharing

Se implementó la funcionalidad para compartir recetas a través de apps externas. Al pulsar el botón de compartir en el detalle de una receta, la pantalla se captura como imagen y se abre el selector nativo de Android para elegir la app destino. El archivo temporal se elimina automáticamente una vez que el proceso de compartir finaliza, y se muestra un estado de carga mientras se prepara la imagen.

**Video explicación:**

### Definition of Done

| Criterio | Descripción | ✅ |
|----------|-------------|---|
| Share button exists | FAB o ítem de menú dispara el flujo de compartir | ✅ |
| Composable captured | La tarjeta de receta se renderiza como Bitmap | ✅ |
| Image includes content | La imagen exportada incluye foto del plato, título e ingredientes | ✅ |
| Share sheet opens | Se abre el selector nativo de apps de Android | ✅ |
| Multiple apps work | Compatible con WhatsApp, Telegram, Email, etc. | ✅ |
| File cleanup | Los archivos temporales se eliminan después de compartir | ✅ |
| Loading state | Se muestra progreso mientras se prepara la imagen para compartir | ✅ |
