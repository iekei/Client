FROM ://microsoft.com

# GUI環境、オーディオ、およびLWJGL（Minecraftの描画エンジン）に必要なライブラリをインストール
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
       xvfb x11vnc fluxbox novnc websockify \
       libgl1-mesa-dri libgl1-mesa-glx libgles2-mesa libegl1-mesa \
       libpulse0 libasound2 \
    && apt-get clean && rm -rf /var/lib/lists/*

# VNCおよびnoVNC用のポートを環境変数として設定
ENV DISPLAY=:1
