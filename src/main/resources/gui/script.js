// モジュールがON/OFFされたときに呼ばれる関数
function toggleModule(moduleName, isEnabled) {
    console.log(`[UI] ${moduleName} が ${isEnabled ? 'ON' : 'OFF'} になりました`);

    // Minecraftに組み込んだ際、Java側に信号を送る処理（JCEFのブリッジ機能）
    // ※ブラウザ単体で開いている時はエラーにならないように条件分岐しています
    if (window.cefQuery) {
        window.cefQuery({
            request: `ToggleModule:${moduleName}:${isEnabled}`,
            onSuccess: function(response) {
                console.log("Java側で処理成功: " + response);
            },
            onFailure: function(error_code, error_message) {
                console.error("Java連携エラー: " + error_message);
            }
        });
    }
}