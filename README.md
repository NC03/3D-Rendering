# 3D-Rendering
3D Rendering Environment from scratch using Java

```bash
ffmpeg -r 30 -i video/%03d.png -c:v libx264 -vf fps=25 -pix_fmt yuv420p out.mp4
```
```bash
ffmpeg -framerate 30 -pattern_type glob -i 'genImage/*.png' -c:v libx264 -pix_fmt yuv420p out2.mp4
```
```Bash
ffmpeg -framerate 30 -pattern_type glob -i "*.png" -c:v libx264 -pix_fmt yuv420p out.mp4
```